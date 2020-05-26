# Klipper Linux Distribution

## Overview

Single purpose Linux Distro aimed at providing a Klipper-3D printing setup which can be built and run on any target supported by the Yocto build system.

If you are looking for a desktop like experience, use Armbian, Raspbian, OctoPi, Debian, Fedora or whatever desktop distro there is that would run on your target. This is not it.

My goal is to have images require a minimum of manual setup interaction, I'd like to keep fiddling around to a minimum, it should be more like: flash, select your printer, start printing. Perhaps this is better comparable to what you get with the native firmware, where you mostly use the local LCD. However here you get Klipper, which in my opinion is a very good choice, especially for those weaker printer boards.

Currently the image includes Klipper 3D and Octoprint.

## Current State

Right now it's a "work in progress" and by no means complete. I do use it on an Asus Tinker Board S, but there is still a lot to be done in terms of configuration, so the current image is rather a proof of concept and while it does work, it still involves some manual configuration steps. Some important features, like distributing and flashing the Klipper MCU Firmware are also missing.

## TODOs

Random thoughts without any specific order:
* add MCU firmware builds (all MCU firmware variants should be included in the image)
* add lighttpd with a central entry page and a minimalistic web UI

The web UI should at least allow to:
* select and flash the firmware to your printer
* select an existing klipper configuration or upload a custom klipper config
* allow to enable/disable OctoPrint
* backup/restore all important settings

## For Developers

Assuming you had a look at the Yocto manual and installed the required build dependencies, to compile the tinker board s image first setup the environment:

`$ source setup.sh targets/tinker-board-s/`

And build the image:

`$ bitbake klipper-image-tinker-board-s`

Note, that git submodules are being used, so make sure to properly init them after initial cloning.

## Links

If you have no clue what I am talking about:

https://www.klipper3d.org/ <br/>
https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html
