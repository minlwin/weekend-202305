# Database Initialization

Create Database
```
create database balance_management_db;
```

Create Application User
```
create user 'bmdusr'@'localhost' identified by 'bmdpwd';
```

Grant Permission
```
grant all privileges on balance_management_db.* to 'bmdusr'@'localhost';
```