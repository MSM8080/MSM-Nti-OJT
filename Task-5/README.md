# **Integrate vsomeip library on qnx on vm**

## QNX {Running on VM} increase data section size to 5GB
- inside the extra options write: {--data-size='5120'}
![image](1.png)

## Build boost & vsomeip for QNX {Running on VM} on host machine {Ubuntu}

**1. Create a workspace**
	> mkdir -p ~/qnx_workspace && cd ~/qnx_workspace
  	> WORKSPACE=${PWD}
  	> git clone https://github.com/qnx-ports/build-files.git	  

**2. For vsomeip 3.4.10**
- $ git clone https://github.com/qnx-ports/vsomeip.git  -b qnx_3.4.10

**3. For SDP 8.0:**
- $ source ~/qnx800/qnxsdp-env.sh

**4. Clone boost**
- $ cd ~/qnx_workspace
- $ git clone https://github.com/boostorg/boost.git && cd boost	  

**5. For boost 1.78.0**
- $ git checkout boost-1.78.0
- $ git submodule update --init --recursive

**6. For boost 1.78.0: apply an interprocess boost lib patch**
- $ cd libs/interprocess && git apply $WORKSPACE/build-files/ports/boost/interprocess_1.78.0_qnx_7.1.patch
- $ cd -      	  

**7. Apply a tools patch for any boost**
- $ cd tools/build && git apply $WORKSPACE/build-files/ports/boost/tools_qnx.patch
- $ cd $WORKSPACE

**8. SDP 8.0: build and install boost**
- $ BOOST_CPP_VERSION_FLAG="-std=c++17" QNX_PROJECT_ROOT="$(pwd)/boost" make -C build-files/ports/boost install -j4	  

**9. Build vsomeip**
- $ GTEST_ROOT=$GTEST_ROOT TEST_IP_MASTER="<QNX-target-ip-address>" TEST_IP_SLAVE="<Ubuntu-ip-address>" QNX_PROJECT_ROOT="$(pwd)/vsomeip" make -C build-files/ports/vsomeip install -j4


### Change password of root or QNX {Running on VM}
!! this commands inside the QNX terminal !!
- $ passwd qnxuser --> {2981}
- $ passwd root --> {2981}

### Move compiled files intto the target QNX {Running on VM}
**1. correct this shell variable !!**
- $ export CPUVARDIR=x86_64
	  
**2. Move the files to the target** 
- $ TARGET_HOST=<target-ip-address-or-hostname>
- $ scp $QNX_TARGET/$CPUVARDIR/usr/local/lib/libboost* root@$TARGET_HOST:/data/home/root/lib
- $ scp $QNX_TARGET/$CPUVARDIR/usr/local/lib/libvsomeip3* root@$TARGET_HOST:/data/home/root/lib
- $ scp -r $QNX_TARGET/$CPUVARDIR/usr/local/bin/vsomeip_tests root@$TARGET_HOST:/data/home/root/bin

	  
## Build vsomeip client/service app 

### inside target: QNX {Running on VM}
- $ mkdir /data/home/root/bin
- $ export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:/data/home/root/lib"
- $ echo 'export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:/data/home/root/lib"' >> /etc/profile

### inside host machine: Ubuntu
- $ cd ~/qnx_workspace/vsomeip/examples/msm-vsomeip-qnx
- $ mkdir -p build && cd build
- $ source ~/qnx800/qnxsdp-env.sh
- $ export CPUVARDIR=x86_64
- $ export QNX_PROJECT_ROOT=$PWD
- $ cmake .. -DCMAKE_BUILD_TYPE=Debug
- $ make -j12
- $ scp ./<binary_file> root@TARGET_HOST:/data/home/root/bin

  
