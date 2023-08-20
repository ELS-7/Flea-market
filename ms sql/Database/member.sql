
--member
Create table member(
	memIndex int IDENTITY(1,1) not null,

	id			varchar(50) not null,
	password	varchar(100),
	name		varchar(50),
	memberType	varchar(20),
	email		varchar(100),
	lastLogin	datetime,

	regDate		datetime,
	modDate		datetime,

 constraint [PK_member] primary key clustered
(
	memIndex asc
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) on [primary]
go



CREATE NONCLUSTERED INDEX [IX_member_id] ON [dbo].[member]
(
	id ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go

----select * from member
--insert into member(id, password, name, memberType, email, lastLogin, regDate, modDate)
--			values('xenus','xenus','xenus','admin','admin@naver.com',null,getdate(),null)