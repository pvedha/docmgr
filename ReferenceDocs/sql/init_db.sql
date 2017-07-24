insert into jobtitle (title, remarks) values ('Administrator', 'Control over everything');
insert into jobtitle (title, remarks) values ('Teacher', 'Control over students and doc');
insert into jobtitle (title, remarks) values ('Therapist', 'Control over doc');
insert into jobtitle (title, remarks) values ('Councillor', 'Control over doc');

insert into jobtitle (title, remarks, childrights, jobrights, docrights) values ('Administrator', 'Control over everything', 1,1,1);
insert into jobtitle (title, remarks, childrights, jobrights, docrights) values ('Teacher', 'Control over students and doc',1,0,1);
insert into jobtitle (title, remarks, childrights, jobrights, docrights) values ('Therapist', 'Control over doc',0,0,1);
insert into jobtitle (title, remarks, childrights, jobrights, docrights) values ('Councillor', 'Control over doc',0,0,1);


insert into ACTIONSTATES (state, remarks) values ('Created', 'Action Item Created');
insert into ACTIONSTATES (state, remarks) values ('Accepted', 'Action Item Accepted by owner');
insert into ACTIONSTATES (state, remarks) values ('Actioned', 'Action Item completed by owner');
insert into ACTIONSTATES (state, remarks) values ('Closed', 'Action Item accepted and closed by creator');

insert into DOCSTATE (docstate, remarks) values ('Draft', 'Document created or in draft state');
insert into DOCSTATE (docstate, remarks) values ('In-progress', 'Document work in progress');
insert into DOCSTATE (docstate, remarks) values ('Final', 'Document marked as final, no more change expected');