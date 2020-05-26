SUMMARY = "Pattern matching and various utilities for file systems paths."
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/pkginfo/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=8227180126797a0148f94f483f3e1489"

SRC_URI[sha256sum] = "7424f2c8511c186cd5424bbf31045b77435b37a8d604990b79d4e70d741148bb"

inherit pypi setuptools

PYPI_PACKAGE = "pkginfo"
