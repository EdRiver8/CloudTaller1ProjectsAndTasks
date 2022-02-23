USE taller_spring;
DROP TABLE IF EXISTS project_tasks;
DROP TABLE IF EXISTS backlogs;
DROP TABLE IF EXISTS projects;

/************** CREATE TABLES **************/
CREATE TABLE IF NOT EXISTS taller_spring.projects (
  id int NOT NULL	auto_increment,
  project_identifier varchar(10) not null,
  project_name varchar(50) NOT NULL,
  description varchar(255),
  start_date DATE,
  end_dateprojects DATE,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS taller_spring.backlogs(
 id int NOT NULL PRIMARY KEY auto_increment,
 project_identifier varchar(10) not null
);

CREATE TABLE IF NOT EXISTS taller_spring.project_tasks(
  id INT NOT NULL PRIMARY KEY auto_increment,
  task_name VARCHAR(50) NOT NULL,
  project_identifier varchar(10) not null,
  summary VARCHAR(255) not null,
  acceptance_criteria VARCHAR(50),
  status VARCHAR(50),
  priority INT,
  hours DOUBLE,
  start_date_task DATE,
  end_date_task DATE
);



/****** RELATIONSHIPS: ADD FK TO THE TABLES ************/
ALTER TABLE taller_spring.backlogs ADD id_project int NOT NULL;
ALTER TABLE taller_spring.backlogs ADD FOREIGN KEY (id_project) REFERENCES taller_spring.projects(id);

ALTER TABLE taller_spring.project_tasks ADD id_backlog int NOT NULL;
ALTER TABLE taller_spring.project_tasks ADD FOREIGN KEY (id_backlog) REFERENCES taller_spring.backlogs(id);