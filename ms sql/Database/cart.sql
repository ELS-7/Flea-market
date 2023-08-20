--use Xenus
--cart 
--select * from cart
Create table cart(
	cartIndex int IDENTITY(1,1) not null,

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

	regDate			datetime,
	modDate			datetime,

 constraint [PK_cart] primary key clustered
(
	cartIndex asc
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) on [primary]
go

CREATE NONCLUSTERED INDEX [IX_cart_sellerId] ON [dbo].[cart]
(
	sellerId ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go

CREATE NONCLUSTERED INDEX [IX_cart_buyerId] ON [dbo].[cart]
(
	buyerId ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go

