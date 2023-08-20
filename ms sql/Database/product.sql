
--product 
--select * from product
Create table product(
	productIndex int IDENTITY(1,1) not null,

	name			varchar(100) not null,
	price			int,
	tradingPrice	int,
	image			varchar(200),
	keywords		varchar(100),
	description		varchar(max),
	phoneNumber		varchar(20),
	
	regId			varchar(50),
	status			varchar(50),
	useYN			varchar(1),
	
	regDate			datetime,
	modDate			datetime,

 constraint [PK_product] primary key clustered
(
	productIndex asc
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) on [primary]
go

CREATE NONCLUSTERED INDEX [IX_product_name] ON [dbo].[product]
(
	name ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
go





