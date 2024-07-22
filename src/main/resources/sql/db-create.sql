
CREATE TABLE IF NOT EXISTS SiteUser();
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userId text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userEmail text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userFirstName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userLastName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userFullName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS seeArchived boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS seeDeleted boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS deleted boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS ComputateCourse();
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS courseName text;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS courseDescription text;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS deleted boolean;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE ComputateCourse ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS ComputateResearch();
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS name text;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS deleted boolean;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE ComputateResearch ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS ComputateEvent();
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS location point;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS eventName text;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS deleted boolean;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE ComputateEvent ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS ComputateWebsite();
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS name text;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS deleted boolean;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE ComputateWebsite ADD COLUMN IF NOT EXISTS userKey bigint;
