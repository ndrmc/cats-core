-- Migration file V20170111150935__seed_accounts_and_journals.sql created at --

insert into account (code, name,description) values ('borrowed','borrowed', 'Borrowed','Resources which are loaned but have not yet been received at warehouses (Receipt Plan)');
insert into account (code, name,description) values ('allocated', 'allocated'Allocated','Resources commited for dispatch through RRD. In CATS this indicates dispatch allocation');
insert into account (code, name,description) values ('received','Received','Represents resources which are received at the hubs. This account represents Goods Receiving Note (GRN)');
insert into account (code, name,description) values ('dispatched', 'Dispatched','Commodities which are dispatched from the warehouse to FDPs. This account represents Goods Issue Ticket (GIT) records.');
insert into account (code, name,description) values ('delivered','Delivered','Commodities which are received at FDPs. This is equivallent to delivery note (GRN) at FDPs.');
insert into account (code, name,description) values ('lost','Lost','Commodities which had proper record in the system but are not being accounted. This account is used during Delivery, Annual Inventory or Distribution.');
insert into account (code, name,description) values ('stock','Stock','Amount of commodities available at the warehouses');
insert into account (code, name,description) values ('distributed','Distributed','Commodities which were delivered to FDPs and are distributed to beneficiareis. This account represents distribution reports from CMPM');
insert into account (code, name,description) values ('statistics','Statistics','This account contains entries made when commodities are taken into (Beginning Inventory, Donation, Purchase and Loan) and released (Utilization) from the system. Similar to "cash book" account.');
insert into account (code, name,description) values ('utilized','Utilized','');
insert into account (code, name,description) values ('repaid''Repaid','');


insert into journal(name,description) values ('beginning.inventory','Beginning Inventory','Beginning inventory balance');
insert into journal(name,description) values ('donation','Donation','Donation journal to hold gift certificate and pledged amounts');
insert into journal(name,description) values ('goods.received','Goods Receiving','Journal to keep track of commodities which are received at warehouses');
insert into journal(name,description) values ('purchase','Purchase','Purchased commodities');
insert into journal(name,description) values ('dispatch.allocation','Dispatch Allocation','Dispatch plan (commited stock) journal');
insert into journal(name,description) values ('goods.issue','Goods Issue','Journal to keep commodities dispatched from warehouses to FDPs');
insert into journal(name,description) values ('loan','Loan','Resources coming loan from other organization');
insert into journal(name,description) values ('repayment','Repayment','');
insert into journal(name,description) values ('transfer','Transfer','Resources transferred between warehouses within NDRMC');
insert into journal(name,description) values ('annual.inventory''Annual Inventory','Inventory adjustment (loss/gain) from physical inventory');
insert into journal(name,description) values ('utilization','Utilization','Journal to keep distribution information');
insert into journal(name,description) values ('delivery','Delivery','Stock available at the FDPs');
insert into journal(name,description) values ('distribution','Distribution','');