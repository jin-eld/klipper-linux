SUMMARY = "Sentry-Python is an SDK for Sentry."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[sha256sum] = "ff1fa7fb85703ae9414c8b427ee73f8363232767c9cd19158f08f6e4f0b58fc7"

inherit pypi setuptools

PYPI_PACKAGE = "sentry-sdk"

BBCLASSEXTEND = "native nativesdk"
