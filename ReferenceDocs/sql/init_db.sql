insert into ACTIONSTATES (state, remarks) values ('Created', 'Action Item Created');
insert into ACTIONSTATES (state, remarks) values ('Accepted', 'Action Item Accepted by owner');
insert into ACTIONSTATES (state, remarks) values ('Actioned', 'Action Item completed by owner');
insert into ACTIONSTATES (state, remarks) values ('Closed', 'Action Item accepted and closed by creator');

insert into DOCSTATE (docstate, remarks) values ('Draft', 'Document created or in draft state');
insert into DOCSTATE (docstate, remarks) values ('In-progress', 'Document work in progress');
insert into DOCSTATE (docstate, remarks) values ('Final', 'Document marked as final, no more change expected');