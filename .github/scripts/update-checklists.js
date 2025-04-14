const core = require('@actions/core');
const github = require('@actions/github');

const token = process.env.GITHUB_TOKEN;
const repo = process.env.GITHUB_REPOSITORY;
const [owner, repoName] = repo.split('/');
const octokit = github.getOctokit(token);

(async () => {
  const { data: issues } = await octokit.rest.issues.listForRepo({
    owner,
    repo: repoName,
    state: 'all', 
    per_page: 100,
  });

  const parents = {};

  for (const issue of issues) {
    const match = issue.body?.match(/Part of #(\d+)/);
    if (match) {
      const parentId = parseInt(match[1]);
      if (!parents[parentId]) parents[parentId] = [];
      parents[parentId].push({
        number: issue.number,
        title: issue.title,
        state: issue.state,
        url: issue.html_url,
      });
    }
  }

  for (const parentId of Object.keys(parents)) {
    const { data: parent } = await octokit.rest.issues.get({
      owner,
      repo: repoName,
      issue_number: parentId,
    });

    const startTag = '<!-- TASKS-START -->';
    const endTag = '<!-- TASKS-END -->';
    const checklist = parents[parentId]
      .map(
        (sub) =>
          `- [${sub.state === 'closed' ? 'x' : ' '}] [#${sub.number}](${sub.url}) ${sub.title}`
      )
      .join('\n');

    const newBody = parent.body.replace(
      new RegExp(`${startTag}[\\s\\S]*?${endTag}`),
      `${startTag}\n${checklist}\n${endTag}`
    );

    if (newBody !== parent.body) {
      await octokit.rest.issues.update({
        owner,
        repo: repoName,
        issue_number: parentId,
        body: newBody,
      });
      console.log(`✅ Updated parent issue #${parentId}`);
    }
  }
})();