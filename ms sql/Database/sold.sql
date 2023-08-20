--use Xenus
--sold 
--select * from sold
Create table sold(
	soldIndex int IDENTITY(1,1) not null,

	sellerId		varchar(50) not null,
	sellerName		varchar(50) not null,
	
	productIndex	int,
	productName		varchar(100),
	price			int,
	tradingPlace	varchar(100),
	
	buyerId			varchar(50) not null,
	buyerName		varchar(50) not null,
	phoneNumber		varchar(20),
	biddingPrice	varchar(100),
	
	status			varchar(50),
	cartIndex		int,

	regDate			datetime,
	modDate			datetime,

 constraint [PK_sold] primary key clustered
(
	soldIndex asc
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) on [primary]
go

CREATE NONCLUSTERED INDEX [IX_sold_sellerId] ON [dbo].[sold]
(
	sellerId ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go

CREATE NONCLUSTERED INDEX [IX_sold_buyerId] ON [dbo].[sold]
(
	buyerId ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go

