REGISTRATION:
-------------
CREATE TABLE ADMINS
(
 ADMIN_ID BIGINT(10) NOT NULL,
 EMAIL_ID VARCHAR(50) NOT NULL,
 PWD VARCHAR(50) NOT NULL,
 STATUS INT(1) NOT NULL,
 IS_FTL INT(1) NOT NULL,
 CREATED_BY        BIGINT(10) NULL,
 CREATED_DATE      BIGINT NULL,
 MODIFIED_BY       BIGINT(10) NULL,
 MODIFIED_DATE     BIGINT NULL,
 VERSION           INT(1) NULL,
 DELETED           INT(1) NULL,
 CONSTRAINT ADMINS_PK PRIMARY KEY (ADMIN_ID),
 CONSTRAINT ADMINS_EMAILID_UNIQUE UNIQUE (EMAIL_ID)
);


CREATE TABLE ADMIN_DETAILS
(
 ADMIN_NAME VARCHAR(25) NOT NULL,
 ADMIN_MOBILE VARCHAR(10) NOT NULL,
 OFFICE_LANDLINE VARCHAR(15) NULL,
 ADMIN_ADDRESS VARCHAR(100) NULL,
 ADMIN_ST_OR_UT INT(4) NOT NULL,
 GSTIN_NO VARCHAR(20) NOT NULL,
 EFFECTIVE_DATE BIGINT NULL,
 CREATED_BY        BIGINT(10) NOT NULL,
 CREATED_DATE      BIGINT NULL,
 MODIFIED_BY       BIGINT(10) NULL,
 MODIFIED_DATE     BIGINT NULL,
 VERSION           INT(1) NULL,
 DELETED           INT(1) NULL,
 CONSTRAINT ADMIN_DETAILS_PK PRIMARY KEY (CREATED_BY),
 CONSTRAINT ADMIN_DETAILS_STORUT_FK FOREIGN KEY (ADMIN_ST_OR_UT) REFERENCES STATES_AND_UT_ENUM(ID)
);

CREATE TABLE ACCOUNT_ACTIVATION (
    ADMIN_ID BIGINT(10) NOT NULL,
    REQUEST_TYPE INT(1) NOT NULL,
    ACTIVATION_CODE  VARCHAR(50) NOT NULL,
    CREATED_DATE BIGINT NULL,

    CONSTRAINT ACCOUNT_ACTIVATION_PK PRIMARY KEY (ADMIN_ID,REQUEST_TYPE)
);


CREATE TABLE USER_DETAILS (
    USER_ID BIGINT(10) NOT NULL,
    PWD VARCHAR(50) NOT NULL,
    USER_NAME VARCHAR(25) NOT NULL,
    USER_MOBILE VARCHAR(10) NOT NULL,
    USER_ADDRESS VARCHAR(100) NOT NULL,
    USER_EMAIL VARCHAR(50) NULL,
    STATUS INT(1) NOT NULL,
    CREATED_BY        BIGINT(10) NULL,
    CREATED_DATE      BIGINT NULL,
    MODIFIED_BY       BIGINT(10) NULL,
    MODIFIED_DATE     BIGINT NULL,
    VERSION           INT(1) NULL,
    DELETED           INT(1) NULL,
    CONSTRAINT USER_DETAILS_PK PRIMARY KEY (USER_ID)
);
ALTER TABLE USER_DETAILS MODIFY COLUMN USER_ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE USER_DETAILS AUTO_INCREMENT=1000001;

alter table user_details change id USER_ID BIGINT(10) NOT NULL;
SELECT * FROM USER_DETAILS WHERE CREATED_BY=1212121212 AND DELETED=0;

select * from user_details;

update user_details set status =1 where user_id=111111;



-------------

CREATE TABLE CVO_DATA (
	ID BIGINT(10) NOT NULL,
	CVO_NAME VARCHAR(50) NOT NULL,
	CVO_ADDRESS VARCHAR(100) NOT NULL,
	CVO_CONTACT VARCHAR(15) NOT NULL,
	CVO_CAT INT(1) NOT NULL,
	IS_GST_REG INT(1) NOT NULL,
	CVO_TIN VARCHAR(20) NULL,
	CVO_EMAIL VARCHAR(50) NULL,
	CVO_PAN VARCHAR(15) NULL,
	OBAL VARCHAR(13) NOT NULL,
	CBAL VARCHAR(13) NOT NULL,
	EBAL VARCHAR(13) NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT CVO_DATA_PK PRIMARY KEY (ID)
);
ALTER TABLE CVO_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE CVO_DATA AUTO_INCREMENT=1000001;

SELECT * FROM CVO_DATA;

CREATE TABLE CVO_BALANCE_DATA (
 ID 		  BIGINT(10) NOT NULL AUTO_INCREMENT,
 REF_ID 	  BIGINT(10) NOT NULL,
 CVOFLAG 	  INT(1) NOT NULL,
 INV_REF_NO   VARCHAR(30) NOT NULL,
 INV_DATE 	  BIGINT(20) NOT NULL,
 TRANS_TYPE   INT(1) NOT NULL,
 CVO_CAT 	  INT(1) NOT NULL,
 CVO_REFID 	  BIGINT(10) NOT NULL,
 AMOUNT 	  VARCHAR(13) NOT NULL,
 CBAL_AMOUNT  VARCHAR(20) NOT NULL,
 DISCOUNT 	  VARCHAR(10) NULL,
 CREATED_BY      		BIGINT(10) NULL,
 CREATED_DATE  			BIGINT NULL,
 MODIFIED_BY          	BIGINT(10) NULL,
 MODIFIED_DATE         	BIGINT NULL,
 VERSION               	INT(1) NULL,
 DELETED               	INT(1) NULL,

 CONSTRAINT CVO_BALANCE_DATA_PK PRIMARY KEY (ID)
);


SELECT * FROM CVO_BALANCE_DATA;

CREATE TABLE BANK_DATA (
	ID BIGINT(10) NOT NULL,
	BANK_CODE VARCHAR(20) NULL,
	BANK_NAME VARCHAR(25) NOT NULL,
	BANK_ACC_NO VARCHAR(20) NOT NULL,
	BANK_BRANCH VARCHAR(50) NULL,
	BANK_IFSC_CODE VARCHAR(15) NULL,
	ACC_OB DECIMAL(13,2) NOT NULL,
	ACC_CB DECIMAL(13,2) NOT NULL,
	BANK_ADDR VARCHAR(250) NULL,
	OD_AND_LOAN_ACCEPTABLE_BAL VARCHAR(13) NOT NULL DEFAULT "NA",
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT BANK_DATA_PK PRIMARY KEY (ID)
);
ALTER TABLE BANK_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE BANK_DATA AUTO_INCREMENT=1000001;

SELECT * FROM BANK_DATA;

CREATE TABLE FLEET_DATA (
	ID BIGINT(10) NOT NULL,
	VEHICLE_NO VARCHAR(10) NOT NULL,
	VEHICLE_MAKE VARCHAR(25) NOT NULL,
	VEHICLE_TYPE INT(1) NOT NULL,
	VEHICLE_USUAGE INT(1) NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT FLEET_DATA_PK PRIMARY KEY (ID)
);
ALTER TABLE FLEET_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE FLEET_DATA AUTO_INCREMENT=1000001;

SELECT * FROM FLEET_DATA ;

UPDATE FLEET_DATA SET CREATED_BY=1212121212 WHERE ID=1000001;
SELECT * FROM STAFF_DATA;




CREATE TABLE QUOTATIONS (
	ID BIGINT NOT NULL,
	SR_NO VARCHAR(30) NOT NULL,
	QTN_DATE BIGINT NOT NULL,
	CUSTOMER_ID BIGINT NOT NULL,
	STAFF_ID BIGINT NOT NULL,
	QTN_AMOUNT VARCHAR(13) NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT QUOTATIONS_PK PRIMARY KEY (ID)
);
ALTER TABLE QUOTATIONS MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE QUOTATIONS AUTO_INCREMENT=1000001;

CREATE TABLE QUOTATION_DETAILS (
	ID BIGINT(10) NOT NULL,
	QTN_ID BIGINT NOT NULL,
	JOB_ID VARCHAR(25) NOT NULL,
	PI_NUMBER BIGINT(10) NOT NULL,
	BILL_NUMBER  BIGINT(10) NOT NULL,
	PROD_CODE BIGINT(10) NOT NULL,
    RAW_MATERIALS INT(4) NOT NULL,
	QUANTITY INT(5) NOT NULL,
	UNIT_TYPE INT(1) NOT NULL,
	UNIT_RATE VARCHAR(8) NOT NULL,
	DISC_UNIT_RATE VARCHAR(8) NOT NULL,
	BASIC_AMOUNT VARCHAR(10) NOT NULL,
	CUSTOMER_FILENAME VARCHAR(25) NOT NULL,
	IGST_AMOUNT VARCHAR(10) NOT NULL,
	SGST_AMOUNT VARCHAR(10) NOT NULL,
	CGST_AMOUNT VARCHAR(10) NOT NULL,
	PROD_AMOUNT VARCHAR(10) NOT NULL,
	FOOT_NOTES VARCHAR(100) NOT NULL,
	VATP VARCHAR(5) NOT NULL,

	CONSTRAINT QUOTATION_DETAILS_PK PRIMARY KEY (ID),
	CONSTRAINT QUOTATION_DETAILS_FK FOREIGN KEY (QTN_ID) REFERENCES QUOTATIONS(ID)
);
ALTER TABLE QUOTATION_DETAILS MODIFY COLUMN JOB_ID VARCHAR(25) null;
ALTER TABLE QUOTATION_DETAILS MODIFY COLUMN PI_NUMBER BIGINT(10) null;
ALTER TABLE QUOTATION_DETAILS MODIFY COLUMN BILL_NUMBER  BIGINT(10) NULL;
ALTER TABLE QUOTATION_DETAILS MODIFY COLUMN RAW_MATERIALS INT(4)  NULL;
ALTER TABLE QUOTATION_DETAILS MODIFY COLUMN UNIT_TYPE INT(1) NULL;
ALTER TABLE QUOTATION_DETAILS MODIFY COLUMN CUSTOMER_FILENAME VARCHAR(25)  NULL;


	


ALTER TABLE QUOTATION_DETAILS AUTO_INCREMENT=1000001;
ALTER TABLE QUOTATION_DETAILS add VATP VARCHAR(5) NOT NULL;



CREATE TABLE PRODUCT_CATEGORY_DATA (
	ID INT(4) NOT NULL,
	CAT_CODE VARCHAR(8) NOT NULL,
	CAT_NAME VARCHAR(25) NOT NULL,
	CAT_DESC VARCHAR(60) NOT NULL,
	CAT_TYPE INT(1) NOT NULL,
	DELETED       	INT(1) NOT NULL,
	CONSTRAINT PRODUCT_CATEGORY_DATA_PK PRIMARY KEY (ID)
);

/*
CREATE TABLE PRODUCT_DATA (
	ID BIGINT(10) NOT NULL,
	PROD_CODE INT(4) NOT NULL,
    RAWMAT_CODE INT(4) NOT NULL,
	UNIT_TYPE INT(1) NOT NULL,
	UNITS INT(1) NOT NULL,
	GSTP VARCHAR(5) NOT NULL,
	SECURITY_DEPOSIT VARCHAR(8) NOT NULL,
	OP_STOCK INT NOT NULL,
	LANDING_RATE VARCHAR(8) NOT NULL,
	SELLING_RATE VARCHAR(8) NOT NULL,
	EFFECTIVE_DATE 	BIGINT NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT PRODUCT_MASTER_PK PRIMARY KEY (ID)
);
ALTER TABLE PRODUCT_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE PRODUCT_DATA AUTO_INCREMENT=1000001;
*/


CREATE TABLE PRODUCT_DATA (
	ID BIGINT(10) NOT NULL,
	PROD_CODE INT(4) NOT NULL,
    RAWMAT_CODE INT(4) NOT NULL,
	UNIT_TYPE INT(1) NOT NULL,
	UNITS INT(1) NOT NULL,
	GSTP VARCHAR(5) NOT NULL,
	OPENING_STOCK INT(4) NOT NULL,
	CURRENT_STOCK INT(4) NOT NULL,
	PURCHASE_PRICE VARCHAR(8) NOT NULL,
	EFFECTIVE_DATE 	BIGINT NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT PRODUCT_MASTER_PK PRIMARY KEY (ID)
);
ALTER TABLE PRODUCT_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE PRODUCT_DATA AUTO_INCREMENT=1000001;
alter table PRODUCT_DATA modify  prod_code int(20);

/*
CREATE TABLE ADMIN_STOCK_DATA (
	ID 				BIGINT(10) NOT NULL,
	REF_ID 			BIGINT(10) NOT NULL,				-- ID of the transaction of corresponding module
	STOCK_FLAG 		INT(1) NOT NULL DEFAULT 0,			-- FLAG for the module
	INV_NO 			VARCHAR(30) NOT NULL,
	TRANS_DATE 		BIGINT NOT NULL,
	TRANS_TYPE 		INT(1) NOT NULL,
	PROD_CODE 		INT(2) NOT NULL,
	RAWMAT_CODE 	INT(2) NOT NULL,				
	STOCK_UNITS 	INT(4) NOT NULL,
	UNIT_TYPE 		INT(1) NOT NULL,
	CVO_ID 			BIGINT NOT NULL,
	DISCOUNT 		VARCHAR(8) NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
 	MODIFIED_BY   	BIGINT(10) NULL,
 	MODIFIED_DATE 	BIGINT NULL,
 	VERSION       	INT(1) NULL,
 	DELETED       	INT(1) NULL,
	CONSTRAINT ADMIN_STOCK_DATA_PK PRIMARY KEY (ID)
);
ALTER TABLE ADMIN_STOCK_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE ADMIN_STOCK_DATA AUTO_INCREMENT=1000001;
*/


CREATE TABLE ADMIN_STOCK_DATA (
	ID 				BIGINT(10) NOT NULL,
	REF_ID 			BIGINT(10) NOT NULL,				-- ID of the transaction of corresponding module
	STOCK_FLAG 		INT(1) NOT NULL DEFAULT 0,			-- FLAG for the module
	INV_NO 			VARCHAR(30) NOT NULL,
	TRANS_DATE 		BIGINT NOT NULL,
	TRANS_TYPE 		INT(1) NOT NULL,
	PROD_CODE 		INT(2) NOT NULL,
	RAWMAT_CODE 	INT(2) NOT NULL,				
	STOCK_UNITS 	INT(4) NOT NULL,
	UNIT_TYPE 		INT(1) NOT NULL,
	CVO_ID 			BIGINT NOT NULL,
	DISCOUNT 		VARCHAR(8) NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
 	MODIFIED_BY   	BIGINT(10) NULL,
 	MODIFIED_DATE 	BIGINT NULL,
 	VERSION       	INT(1) NULL,
 	DELETED       	INT(1) NULL,
	CONSTRAINT ADMIN_STOCK_DATA_PK PRIMARY KEY (ID)
);
ALTER TABLE ADMIN_STOCK_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE ADMIN_STOCK_DATA AUTO_INCREMENT=1000001;



CREATE TABLE PRODUCT_PRICES_DATA (
	ID BIGINT(10) NOT NULL,
	PROD_CODE INT(4) NOT NULL,
	BASE_PRICE VARCHAR(8) NOT NULL,
	SGST_PRICE VARCHAR(8) NOT NULL,
	CGST_PRICE VARCHAR(8) NOT NULL,
	RSP VARCHAR(8) NOT NULL,
	MONTH INT(2) NOT NULL,
	YEAR INT(4) NOT NULL,
	CREATED_BY    	BIGINT(10) NULL,
	CREATED_DATE  	BIGINT NULL,
	MODIFIED_BY   	BIGINT(10) NULL,
	MODIFIED_DATE 	BIGINT NULL,
	VERSION       	INT(1) NULL,
	DELETED       	INT(1) NULL,
	CONSTRAINT PRODUCT_PRICES_MASTER_PK PRIMARY KEY (ID)
);
ALTER TABLE PRODUCT_PRICES_DATA MODIFY COLUMN ID BIGINT(10) AUTO_INCREMENT;
ALTER TABLE PRODUCT_PRICES_DATA AUTO_INCREMENT=1000001;



CREATE TABLE AGENCY_SERIAL_NOS (
 SI_SNO INT(5) NULL,
 CS_SNO INT(5) NULL,
 PR_SNO INT(5) NULL,
 SR_SNO INT(5) NULL,
 QT_SNO INT(5) NULL,
 DC_SNO INT(5) NULL,
 RCPTS_SNO INT(5) NULL,
 PMTS_SNO INT(5) NULL,
 BT_SNO INT(5) NULL,
 CN_SNO INT(5) NULL,
 DN_SNO INT(5) NULL,
 FY INT(5) NULL,
 CREATED_BY BIGINT(10) NOT NULL,
 CONSTRAINT AGENCY_SERIAL_NO_PK PRIMARY KEY (CREATED_BY)
);