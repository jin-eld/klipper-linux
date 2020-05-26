SUMMARY = "WebSocket client for python. hybi13 is supported."
HOMEPAGE = "https://github.com/websocket-client/websocket-client.git"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c4c4a98fbc4836b81c8c64d6ecb01fc1"

SRC_URI[sha256sum] = "1fd5520878b68b84b5748bb30e592b10d0a91529d5383f74f4964e72b297fd3a"

inherit pypi setuptools

PYPI_PACKAGE = "websocket_client"

RDEPENDS_${PN} += "python-backports-ssl"
