--default data

--insert into member(id, password, name, memberType, email, lastLogin, regDate, modDate)
--			values('seller','seller','판매자','seller','seller@naver.com',null,getdate(),null)

			
--insert into member(id, password, name, memberType, email, lastLogin, regDate, modDate)
--			values('buyer','buyer','구매자','buyer','buyer@naver.com',null,getdate(),null)


select * from member
--update member set id = 'seller1' where memindex =2
--update member set password = 'password!'

select * from product

--insert into product(name, price, tradingPrice, image, keywords, description, phoneNumber, regId, status, useYN, regDate, modDate)
			select  replace(name,'10','15'), price + 10000, tradingPrice + 10000, image, keywords, description, phoneNumber, regId, status, useYN, regDate, modDate
			from product where productindex = 16

--update product set description = replace(description,'?','>')


--select * delete from sold where soldindex = 2