/****** QUERIES ************/
#PROJECTS:
INSERT INTO taller_spring.projects (project_identifier, project_name, description, start_date, end_dateprojects)
	VALUES ('AAAAAA6', 'Taller Spring', 'Practica con Spring Boot', '2021-12-30', '2021-12-30');

INSERT INTO taller_spring.projects (project_identifier, project_name, description, start_date, end_dateprojects)
VALUES ('BBBB4', 'Scrum Project', 'Practica de Scrum', '2021-12-30', '2021-12-30');

INSERT INTO taller_spring.projects (project_identifier, project_name, description, start_date, end_dateprojects)
VALUES ('CCCCC5', 'Singleton', 'Practica con factory y singleton', '2021-12-30', '2021-12-30');

#BACKLOGS:
INSERT INTO taller_spring.backlogs (id_project, project_identifier) VALUES (1,'AAAAAA6');
INSERT INTO taller_spring.backlogs (id_project, project_identifier) VALUES (2,'BBBB4');
INSERT INTO taller_spring.backlogs (id_project, project_identifier) VALUES (3,'CCCCC5');

#PROJECT_TASKS:
INSERT INTO taller_spring.project_tasks (task_name, project_identifier, summary, acceptance_criteria, status, priority, hours, start_date_task, end_date_task, id_backlog)
	 VALUES ('Crear Entidades', 'AAAAAA6', 'Realizar las entidades para los DTO', 'Aceptada', 'Started', 3, 2.5, '2021-12-30', '2021-12-30', 1);
INSERT INTO taller_spring.project_tasks (task_name, project_identifier, summary, acceptance_criteria, status, priority, hours, start_date_task, end_date_task, id_backlog)
    VALUES ('Crear Servicios', 'AAAAAA6', 'Analizar la logica del negocio', 'Aceptada', 'In Progress', 1, 5, '2021-12-30', '2021-12-30', 1);
INSERT INTO taller_spring.project_tasks (task_name, project_identifier, summary, acceptance_criteria, status, priority, hours, start_date_task, end_date_task, id_backlog)
    VALUES ('Realizar Epicas', 'BBBB4', 'Revisar las epicas que requiere el proyecto', 'Aceptada', 'Completed', 1, 7, '2021-12-30', '2021-12-30', 2);
INSERT INTO taller_spring.project_tasks (task_name, project_identifier, summary, acceptance_criteria, status, priority, hours, start_date_task, end_date_task, id_backlog)
    VALUES ('Crear Diseño', 'CCCCC5', 'Realizar analisis para la implementacion', 'Aceptada', 'Started', 5, 4.5, '2021-12-30', '2021-12-30', 3);