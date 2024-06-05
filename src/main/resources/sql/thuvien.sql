drop database ThuVien
go

create database ThuVien
go

use ThuVien
go

 
CREATE TABLE [DauSach] (
  [MaDS] varchar(20) PRIMARY KEY,
  [TenS] nvarchar(200) NOT NULL,
  [TacGia] nvarchar(200) NOT NULL,
  [NhaXB] nvarchar(200) NOT NULL,
  [NamXB] varchar(10) NOT NULL,
  [DonGia] float not null
)
GO

CREATE TABLE [Sach] (
  [MaS] varchar(20) PRIMARY KEY,
  [MaDS] varchar(20),
  [Vitri] nvarchar(200) NOT NULL,
  [TinhTrang] nvarchar(200) NOT NULL
)
GO

CREATE TABLE [NhanVien] (
  [MaNV] varchar(20) PRIMARY KEY,
  [TenNV] nvarchar(200) NOT NULL,
  [SoDT] varchar(20) UNIQUE,
  [pass] nvarchar(255) NOT NULL,
  [VaiTro] nvarchar(200) NOT NULL
)
GO

CREATE TABLE [DocGia] (
  [MaDG] varchar(20) PRIMARY KEY,
  [tenDG] nvarchar(200) NOT NULL,
  [SoDT] varchar(20) not null,
  [Lop] nvarchar(255) NOT NULL,
  [Khoa] nvarchar(200) NOT NULL
)
GO

CREATE TABLE [MuonTra] (
  [SoPH] varchar(20) PRIMARY KEY,
  [MaDG] varchar(20) NOT NULL,
  [MaNV] varchar(20) NOT NULL,
  [NgayM] Date NOT NULL,
  [NgayHT] Date NOT NULL
)
GO

CREATE TABLE [ChiTietMuonTra] (
  [SoPH] varchar(20),
  [MaS] varchar(20),
  [NgayT] date null,
  [TienPhat] float null,
  PRIMARY KEY ([SoPH], [MaS])
)
GO

ALTER TABLE [Sach] ADD FOREIGN KEY ([MaDS]) REFERENCES [DauSach] ([MaDS])
GO

ALTER TABLE [MuonTra] ADD FOREIGN KEY ([MaNV]) REFERENCES [NhanVien] ([MaNV])
GO

ALTER TABLE [MuonTra] ADD FOREIGN KEY ([MaDG]) REFERENCES [DocGia] ([MaDG])
GO

ALTER TABLE [ChiTietMuonTra] ADD FOREIGN KEY ([MaS]) REFERENCES [Sach] ([MaS])
GO

ALTER TABLE [ChiTietMuonTra] ADD FOREIGN KEY ([SoPH]) REFERENCES [MuonTra] ([SoPH])
GO
