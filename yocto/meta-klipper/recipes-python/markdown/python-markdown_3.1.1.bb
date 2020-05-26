SUMMARY = "A Python implementation of John Gruber's Markdown."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=745aaad0c69c60039e638bff9ffc59ed"

inherit pypi setuptools

PYPI_PACKAGE = "Markdown"
#PYPI_SRC_URI = "https://files.pythonhosted.org/packages/ac/df/0ae25a9fd5bb528fe3c65af7143708160aa3b47970d5272003a1ad5c03c6/Markdown-${PV}.tar.gz"

SRC_URI[md5sum] = "d84732ecc65b3a1bff693d9d4c24277f"
SRC_URI[sha256sum] = "2e50876bcdd74517e7b71f3e7a76102050edec255b3983403f1a63e7c8a41e7a"

BBCLASSEXTEND = "native"
