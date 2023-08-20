--use Xenus
--wish 
--select * from wish
Create table wish(
	wishIndex int IDENTITY(1,1) not null,

	memberIndex		int,
	productIndex	int,

	productName		varchar(100),
	price			int,
	useYN			varchar(1),

	regDate			datetime,
	modDate			datetime,

 constraint [PK_wish] primary key clustered
(
	wishIndex asc
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) on [primary]
go

CREATE NONCLUSTERED INDEX [IX_wish_memberIndex] ON [dbo].[wish]
(
	memberIndex ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go

