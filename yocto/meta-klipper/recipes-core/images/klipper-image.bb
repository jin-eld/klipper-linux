COMPATIBLE_MACHINE = "(tinker-board-s|cubietruck)"

inherit image

WKS_FILE_tinker-board-s = "tinker-board-s.wks.in"
WKS_FILE_cubietruck = "cubietruck.wks.in"

IMAGE_FSTYPES = "wic.bz2 wic.bmap"

IMAGE_BASENAME = "klipper-image"

do_image_wic[depends] += "\
    virtual/bootloader:do_deploy \
    virtual/kernel:do_deploy \
    klipper-linux-rootfs:do_image_complete \
    "

