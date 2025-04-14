// .github/scripts/update-parent-checklists.js

const { Octokit } = require("@octokit/core");

const token = process.env.GITHUB_TOKEN;
const repo = process.env.GITHUB_REPOSITORY;
const [owner, repoName] = repo.split("/");

const octokit = new Octokit({ auth: token });

async function run() {
  const issues = await octokit.request("GET /repos/{owner}/{repo}/issues", {
    owner,
    repo: repoName,
    state: "all",
    per_page: 100,
  });

  for (const issue of issues.data) {
    const body = issue.body || "";
    if (!body.includes("<!-- TASKS-START -->") || !body.includes("<!-- TASKS-END -->")) continue;

    const parentIssueNumber = issue.number;

    const subIssues = issues.data.filter(sub =>
      sub.body?.includes(`Part of #${parentIssueNumber}`)
    );

    const checklistItems = subIssues.map(sub => {
      const isClosed = sub.state === "closed";
      const checkbox = isClosed ? "[x]" : "[ ]";
      return `- ${checkbox} [#${sub.number}](${sub.html_url}) ${sub.title}`;
    });

    const updatedBody = body.replace(
      /<!-- TASKS-START -->([\s\S]*?)<!-- TASKS-END -->/,
      `<!-- TASKS-START -->\n${checklistItems.join("\n")}\n<!-- TASKS-END -->`
    );

    if (updatedBody !== body) {
      await octokit.request("PATCH /repos/{owner}/{repo}/issues/{issue_number}", {
        owner,
        repo: repoName,
        issue_number: parentIssueNumber,
        body: updatedBody,
      });

      console.log(`✅ Updated parent issue #${parentIssueNumber}`);
    } else {
      console.log(`ℹ️ No update needed for issue #${parentIssueNumber}`);
    }
  }
}

run().catch(err => {
  console.error("❌ Script failed", err);
  process.exit(1);
});