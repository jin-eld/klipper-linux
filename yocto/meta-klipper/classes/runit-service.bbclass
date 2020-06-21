# Imported from:
# https://git.digitalstrom.org/dss-oe/dss-oe/blob/master/yocto/dS/meta-digitalstrom-devel/classes/runit-service.bbclass
#
# License: MIT
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
#
#
# Class that handles control of the runit service, similar to update-rc.d class.
#
# RUNIT_SERVICES: name of the services that we want to control
# RUNIT_SERVICE_PACKAGES: list of packages that should be handled by this class
# RUNIT_SERVICE_AUTOSTART: allows to control automatic service startup after
#                          package installation

RUNIT_SERVICE_PN ?= "${PN}"
RUNIT_SERVICE_AUTOSTART ?= "1"
RUNIT_SERVICE_DIR ?= "/var/service"

RDEPENDS_${PN}_append = "busybox busybox-runit"

runit_service_prerm() {
if [ "x$D" = "x" ]; then
    if [ ! -f "/etc/runit/${RUNIT_SERVICE_NAME}/down" ]; then
        if [  -f "/etc/runit/${RUNIT_SERVICE_NAME}/supervise/stat" ]; then
            if [ "`cat /etc/runit/${RUNIT_SERVICE_NAME}/supervise/stat`" = "run" ]; then
                echo "Stopping ${RUNIT_SERVICE_NAME}..."
                sv force-stop /etc/runit/${RUNIT_SERVICE_NAME} || true
            fi # current service status
        fi # suervice existence
    fi # service "down" file
fi # live image check
}

runit_service_postinst_ontarget() {
    # only link when autostart is true, but otherwise do not modify existing
    # rootfs configuration, i.e. dont force disable
    if [ "${RUNIT_SERVICE_AUTOSTART}" -eq "1" ]; then
        if [ -d "/etc/runit/${RUNIT_SERVICE_NAME}" ] && [ ! -L "${RUNIT_SERVICE_DIR}/${RUNIT_SERVICE_NAME}" ]; then
            ln -s "/etc/runit/${RUNIT_SERVICE_NAME}" "${RUNIT_SERVICE_DIR}/${RUNIT_SERVICE_NAME}"
        fi 
    fi 
}

runit_service_postrm() {
if [ "x$D" = "x" ]; then
    if [ ! -f "/etc/runit/${RUNIT_SERVICE_NAME}/down" ]; then
        if [ -n "${RUNIT_SERVICE_NAME}" ] && [ -d "/etc/runit/${RUNIT_SERVICE_NAME}" ]; then
            rm -rf "/etc/runit/${RUNIT_SERVICE_NAME}"
        fi
    fi
fi
}

def runit_service_after_parse(d):
    if d.getVar('RUNIT_SERVICES') == None:
        raise bb.build.FuncFailed("%s inherits runit-service but doesn't set RUNIT_SERVICES" % d.getVar('FILE'))

python __anonymous() {
    runit_service_after_parse(d)
}

PACKAGESPLITFUNCS_prepend = "populate_packages_runit_service "

populate_packages_runit_service[vardeps] += "runit_service_prerm runit_service_postinst_ontarget runit_service_postrm"

python populate_packages_runit_service() {
    def runit_service(pkg, service):
        localdata = bb.data.createCopy(d)
        localdata.setVar("RUNIT_SERVICE_NAME", service)
        bb.data.update_data(localdata)

        postinst = d.getVar('pkg_postinst_ontarget_%s' % pkg, True)
        if not postinst:
            postinst = '#!/bin/sh\n'
        postinst += localdata.getVar('runit_service_postinst_ontarget', True)
        d.setVar('pkg_postinst_ontarget_%s' % pkg, postinst)

        prerm = d.getVar('pkg_prerm_%s' % pkg, True)
        if not prerm:
            prerm = '#!/bin/sh\n'
        prerm += localdata.getVar('runit_service_prerm', True)
        d.setVar('pkg_prerm_%s' % pkg, prerm)

        if d.getVar("RUNIT_SERVICE_PN", True) == service:
            postrm = d.getVar('pkg_postrm_%s' % pkg, True)
            if not postrm:
                postrm = '#!/bin/sh\n'
            postrm += localdata.getVar('runit_service_postrm', True)
            d.setVar('pkg_postrm_%s' % pkg, postrm)

    def runit_services_package(pkg):
        localdata = bb.data.createCopy(d)
        overrides = localdata.getVar("OVERRIDES", True)
        localdata.setVar("OVERRIDES", "%s:%s" % (pkg, overrides))
        bb.data.update_data(localdata)

        localdata = bb.data.createCopy(d)
        overrides = localdata.getVar("OVERRIDES", True)
        localdata.setVar("OVERRIDES", "%s:%s" % (pkg, overrides))
        bb.data.update_data(localdata)

        services = (d.getVar("RUNIT_SERVICES", True) or "").split()
        for service in services:
            runit_service(pkg, service)
            
    pkgs = d.getVar("RUNIT_SERVICE_PACKAGES", True)
    if pkgs == None:
        pkgs = d.getVar("RUNIT_SERVICE_PN", True)
        packages = (d.getVar("PACKAGES", True) or "").split()
        if not pkgs in packages and packages != []:
            pkgs = packages[0]
    for pkg in pkgs.split():
        runit_services_package(pkg)
}

