-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
\c postgres

SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'final_capstone_V2'
  AND pid <> pg_backend_pid();

DROP DATABASE IF EXISTS "final_capstone_V2";

DROP ROLE IF EXISTS final_capstone_owner;
DROP ROLE IF EXISTS final_capstone_appuser;