-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'final_capstone_V2';

DROP DATABASE 'final_capstone_V2';

DROP USER final_capstone_owner;
DROP USER final_capstone_appuser;
