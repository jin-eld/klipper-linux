SUMMARY = "Backport/clone of ChainMap for py26, py32, and pypy3."
DESCRIPTION = "\
This module is a polyfill, implementing ChainMap for reasonably-recent versions of Python that do not have collections.ChainMap–namely, Python 2.6, Python 3.2, and PyPy3 releases based on Python 3.2. (It will also work as expected on Python 2.7, PyPy, and Python 3.3 and higher, but it is not needed there since those verions’ collections modules contains a ChainMap implementation.)"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://setup.py;beginline=23;endline=23;md5=fa869eb628865ee985e6a9d306744ac8"

SRC_URI[sha256sum] = "e42aaa4b3e2f66102a11bfd563069704bfbfd84fdcb517b564effd736bf53cd9"

PYPI_PACKAGE = "chainmap"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native nativesdk"

inherit pypi setuptools

