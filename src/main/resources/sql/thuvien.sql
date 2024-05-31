create database ThuVien
go

use ThuVien
go

 
CREATE TABLE [DauSach] (
  [MaDS] varchar(20) PRIMARY KEY,
  [TenS] nvarchar NOT NULL,
  [TacGia] nvarchar NOT NULL,
  [NhaXB] nvarchar NOT NULL,
  [NamXB] varchar(10) NOT NULL,
  [SoLuongBS] int NOT NULL,
  [DonGia] float not null
)
GO

CREATE TABLE [Sach] (
  [MaS] varchar(20) PRIMARY KEY,
  [MaDS] varchar(20),
  [Vitri] nvarchar NOT NULL,
  [TinhTrang] nvarchar NOT NULL
)
GO

CREATE TABLE [NhanVien] (
  [MaNV] varchar(20) PRIMARY KEY,
  [TenNV] nvarchar NOT NULL,
  [SoDT] varchar(20) NOT NULL,
  [pass] nvarchar(255) NOT NULL,
  [VaiTro] nvarchar NOT NULL
)
GO

CREATE TABLE [DocGia] (
  [MaDG] varchar(20) PRIMARY KEY,
  [tenDG] nvarchar NOT NULL,
  [NgaySinh] Date,
  [Lop] nvarchar(255) NOT NULL,
  [Khoa] nvarchar NOT NULL
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
  [NgayT] date not null,
  [TienPhat] float not null,
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
