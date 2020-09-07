-- Таблицы для базы данных по кредитным рискам

-- Удаление структуры
IF OBJECT_ID('GuaranteeLimits', 'U') IS NOT NULL DROP TABLE GuaranteeLimits;
IF OBJECT_ID('GuaranteeNames', 'U') IS NOT NULL DROP TABLE GuaranteeNames;
IF OBJECT_ID('GuaranteeAmounts', 'U') IS NOT NULL DROP TABLE GuaranteeAmounts; --delete
IF OBJECT_ID('GuaranteeReports', 'U') IS NOT NULL DROP TABLE GuaranteeReports;
IF OBJECT_ID('GuaranteeApprovalDocs', 'U') IS NOT NULL DROP TABLE GuaranteeApprovalDocs;
IF OBJECT_ID('GuaranteeApprovalDocTypes', 'U') IS NOT NULL DROP TABLE GuaranteeApprovalDocTypes;
IF OBJECT_ID('Guarantees', 'U') IS NOT NULL DROP TABLE Guarantees;
IF OBJECT_ID('GuaranteeTypes', 'U') IS NOT NULL DROP TABLE GuaranteeTypes;
IF OBJECT_ID('Subsidiaries', 'U') IS NOT NULL DROP TABLE Subsidiaries;
IF OBJECT_ID('CounterpartyPortfolios', 'U') IS NOT NULL DROP TABLE CounterpartyPortfolios;
IF OBJECT_ID('Portfolios', 'U') IS NOT NULL DROP TABLE Portfolios;
IF OBJECT_ID('Committees', 'U') IS NOT NULL DROP TABLE Committees;
IF OBJECT_ID('CommitteeStatuses', 'U') IS NOT NULL DROP TABLE CommitteeStatuses;
IF OBJECT_ID('CommitteeLimits', 'U') IS NOT NULL DROP TABLE CommitteeLimits;
--IF OBJECT_ID('RatingCounterpartyInts', 'U') IS NOT NULL DROP TABLE RatingCounterpartyInts; --delete
IF OBJECT_ID('RatingInternals', 'U') IS NOT NULL DROP TABLE RatingInternals;
IF OBJECT_ID('FinancialStatements', 'U') IS NOT NULL DROP TABLE FinancialStatements;
IF OBJECT_ID('FinancialStatementStandards', 'U') IS NOT NULL DROP TABLE FinancialStatementStandards;
--IF OBJECT_ID('RatingCounterpartyExts', 'U') IS NOT NULL DROP TABLE RatingCounterpartyExts; --delete
IF OBJECT_ID('RatingExternals', 'U') IS NOT NULL DROP TABLE RatingExternals;
IF OBJECT_ID('Counterparties', 'U') IS NOT NULL DROP TABLE Counterparties;
--IF OBJECT_ID('CounterpartyAddons', 'U') IS NOT NULL DROP TABLE CounterpartyAddons; --delete
IF OBJECT_ID('CounterpartyGroups', 'U') IS NOT NULL DROP TABLE CounterpartyGroups;
IF OBJECT_ID('FinancialSectors', 'U') IS NOT NULL DROP TABLE FinancialSectors;
IF OBJECT_ID('RatingCountries', 'U') IS NOT NULL DROP TABLE RatingCountries;
IF OBJECT_ID('Countries', 'U') IS NOT NULL DROP TABLE Countries;
IF OBJECT_ID('CountryTickets', 'U') IS NOT NULL DROP TABLE CountryTickets;
IF OBJECT_ID('RatingValues', 'U') IS NOT NULL DROP TABLE RatingValues; --delete
IF OBJECT_ID('Ratings', 'U') IS NOT NULL DROP TABLE Ratings;
IF OBJECT_ID('RatingGroups', 'U') IS NOT NULL DROP TABLE RatingGroups;
IF OBJECT_ID('RatingAgencies', 'U') IS NOT NULL DROP TABLE RatingAgencies;
IF OBJECT_ID('RiskClasses', 'U') IS NOT NULL DROP TABLE RiskClasses;
IF OBJECT_ID('CurrencyRates', 'U') IS NOT NULL DROP TABLE CurrencyRates;
IF OBJECT_ID('Currencies', 'U') IS NOT NULL DROP TABLE Currencies;
IF OBJECT_ID('UserSettings', 'U') IS NOT NULL DROP TABLE UserSettings;

-- Таблица дочерних компаний
CREATE TABLE Subsidiaries (
	Id int not null identity(1,1) primary key,
	Code int NOT NULL UNIQUE,
	Name nvarchar(255) NOT NULL UNIQUE
)
-- Таблица портфелей
CREATE TABLE Portfolios (
	Id int not null identity(1,1) primary key,
	Name nvarchar(50) not null
)
-- Таблица секторов экономики
CREATE TABLE FinancialSectors (
	Id int not null identity(1,1) primary key,
	Name nvarchar(50) not null unique,
	NameRu nvarchar(50) not null unique
)
-- Таблица стран
CREATE TABLE Countries (
	Id int not null identity(1,1) primary key,
	Name nvarchar(100) not null unique,
	NameRu nvarchar(100) not null unique,
	ShortName nvarchar(2) not null unique,
	Ticker nvarchar(50) null
)
-- Таблица групп комитетов
create table CounterpartyGroups (
	Id int not null identity(1,1) primary key,
	Name nvarchar(20) not null unique
)
-- Таблица контрагентов-банков (или дополнительные поля)
--CREATE TABLE CounterpartyAddons (
--	Id int not null identity(1,1) primary key,
--	Swift nvarchar(50) null,
--	Inn nvarchar(20) null,
--	Ticket nvarchar(20) null
--)
-- Таблица контрагентов
CREATE TABLE Counterparties (
	Id int not null identity(1,1) primary key,
	Name nvarchar(450) not null unique,
	ShortName nvarchar(15) not null unique,
	IntraGroup bit not null default 0,
	DateStart date not null,
	Comment nvarchar(1000) null,
	Monitored bit not null default 0,
	Ticker nvarchar(50) null,
	Inn nvarchar(30) null,
	Swift nvarchar(100) null,
	BankClass nvarchar null,
	FinancialSector_id int foreign key references FinancialSectors(Id),
	Country_id int foreign key references Countries(Id),
	CountryRisk_id int foreign key references Countries(Id),
	RatingDonor_id int foreign key references Counterparties(Id),
	CounterpartyGroup_id int foreign key references CounterpartyGroups(Id)
)
-- Таблица контрагент-портфель
CREATE TABLE CounterpartyPortfolios (
	Counterparty_id int not null,
	Portfolio_id int not null,
	primary key (Counterparty_id, Portfolio_id),
	FOREIGN KEY (Counterparty_id) REFERENCES Counterparties(Id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Portfolio_id) REFERENCES Portfolios(Id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
-- Таблица статусов комитетов
create table CommitteeStatuses (
	Id int not null identity(1,1) primary key,
	Name nvarchar(100) not null unique,
)
-- Таблица ограничений комитетов
create table CommitteeLimits (
	Id int not null identity(1,1) primary key,
	Name nvarchar(100) not null unique
)
-- Таблица комитетов
create table Committees (
	Id int not null identity(1,1) primary key,
	Counterparty_id int foreign key references Counterparties(Id),
	CommitteeStatus_id int foreign key references CommitteeStatuses(Id),
	CommitteeLimit_id int foreign key references CommitteeLimits(Id),
	DateStart date not null,
	Comment nvarchar(1000) null
)
-- Группы рейтингов
create table RatingGroups (
	Id int not null identity(1,1) primary key,
	Number int not null unique,
	Limit bigint not null,
	LimitBank bigint not null
)
-- Таблица значений рейтингов
create table Ratings (
	Id int not null identity(1,1) primary key,
	Score int not null unique,
	Name nvarchar(6) not null unique,
	RatingGroup_id int foreign key references RatingGroups(Id),
)
-- Рейтинговые агенства
create table RatingAgencies (
	Id int not null identity(1,1) primary key,
	Name nvarchar(30) not null unique
)
-- Рейтинги стран
create table RatingCountries (
	Id int not null identity(1,1) primary key,
	DateStart date not null,
	RatingAgency_id int foreign key references RatingAgencies(Id),
	Rating_id int foreign key references Ratings(Id),
	Country_id int foreign key references Countries(Id)
)
-- Класс риска
create table RiskClasses (
	Id int not null identity(1,1) primary key,
	Name nvarchar(10) not null unique
)
-- Виды валют
create table Currencies (
	Id int not null identity(1,1) primary key,
	Name nvarchar(3) not null unique
)
-- Курсы валют на дату
create table CurrencyRates (
	Id int not null identity(1,1) primary key,
	CurrencyFrom_id int foreign key references Currencies(Id),
	CurrencyTo_id int foreign key references Currencies(Id),
	Rate float not null,
	DateReport date not null,
	unique (CurrencyFrom_id, CurrencyTo_id, DateReport)
)
-- Внешний рейтинг контрагентов
create table RatingExternals (
	Id int not null identity(1,1) primary key,
	DateStart date not null,
	Counterparty_id int foreign key references Counterparties(Id),
	RatingAgency_id int foreign key references RatingAgencies(Id),
	Rating_id int foreign key references Ratings(Id),
	unique (DateStart, Counterparty_id, RatingAgency_id)
)
-- Финансовые условия - стандарты
create table FinancialStatementStandards (
	Id int identity(1,1) primary key,
	Name nvarchar(100) not null unique
)
-- Финансовые условия
create table FinancialStatements (
	Id int identity(1,1) primary key,
	DateStart date not null,
	Comment nvarchar(1000),
	Counterparty_id int foreign key references Counterparties(Id),
	Standard_id int foreign key references FinancialStatementStandards(Id),
)
-- Внутренний рейтинг контрагентов
create table RatingInternals (
	Id int identity(1,1) primary key,
	DateStart date not null,
	Conservative bit default 0,
	Analyst nvarchar(30) not null,
	Comment nvarchar(1000) null,
	Counterparty_id int foreign key references Counterparties(Id) not null,
	Rating_id int foreign key references Ratings(Id) not null,
	RatingWc_id int foreign key references Ratings(Id),
	RiskClass_id int foreign key references RiskClasses(Id),
	FinancialStatement_id int foreign key references FinancialStatements(Id)
)
-- ГАРАНТИИ
-- Виды гарантий
create table GuaranteeTypes (
	Id int identity(1,1) primary key,
	Name nvarchar(100) not null unique
)
-- Гарантии
create table Guarantees (
	Id int identity(1,1) primary key,
	Number nvarchar(100) null,
	DateStart date not null,
	AmountInitial bigint null,
	Currency_id int foreign key references Currencies(Id),
	Counterparty_id int foreign key references Counterparties(Id),
	Guarantor_id int foreign key references Counterparties(Id),
	GuaranteeType_id int foreign key references GuaranteeTypes(Id),
	Beneficiar_id int foreign key references Counterparties(Id),
	Subsidiary_id int foreign key references Subsidiaries(Id),
	Comment nvarchar(1000) null
)
-- Суммы гарантий на дату
create table GuaranteeReports (
	Id int identity(1,1) primary key,
	Guarantee_id int foreign key references Guarantees(Id),
	DateReport date not null,
	DateExpiration date null,
	AmountStart bigint not null,
	AmountEnd bigint not null,
	AmountOperation bigint null
)
-- Лимиты на гарантии
create table GuaranteeLimits (
	Id int identity(1,1) primary key,
	Guarantor_id int foreign key references Counterparties(Id),
	DateAgreeStart date not null,
	DateAgreeEnd date not null,
	DateEnd date not null,
	Amount bigint null,
	Currency_id int foreign key references Currencies(Id),
	GuaranteeType_id int foreign key references GuaranteeTypes(Id)
)
-- Виды подтверждающих документов на гарантии
create table GuaranteeApprovalDocTypes (
	Id int identity(1,1) primary key,
	Name nvarchar(255) not null unique
)
-- Подтверждающие документы на гарантии
create table GuaranteeApprovalDocs (
	Id int identity(1,1) primary key,
	Guarantee_id int foreign key references Guarantees(Id),
	DateApproval date not null,
	GuaranteeApprovalDocType_id int foreign key references GuaranteeApprovalDocTypes(Id),
	Number nvarchar(1000) not null
)
create table UserSettings (
	Id int identity(1,1) primary key,
	Username nvarchar(255) not null,
	TableCounterpartyShowId bit,
	TableCounterpartyShowShortName bit,
	TableCounterpartyShowSector bit,
	TableCounterpartyShowCountryOfRisk bit,
	TableCounterpartyShowCountryOfDomicile bit,
	TableCounterpartyShowIntraGroup bit,
	TableCounterpartyShowPortfolio bit,
	TableCounterpartyShowDonor bit,
	TableCounterpartyShowStartDate bit,
	TableCounterpartyShowComment bit,
	TableCounterpartyShowINN bit,
	TableCounterpartyShowSWIFT bit,
	TableCounterpartyShowTicker bit,
	TableCounterpartyShowLastIntRating bit,
	TableCounterpartyShowLastExtRating bit,
	TableCounterpartyShowActiveRating bit,
	TableCounterpartyShowEffectiveRating bit
)
