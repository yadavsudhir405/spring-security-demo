ALTER TABLE dbo.Users ADD isAccountLocked bit default 1;
ALTER TABLE dbo.Users ADD isAccountExpired bit default 1;
ALTER TABLE dbo.Users ADD isPasswordExpired bit default 1;

