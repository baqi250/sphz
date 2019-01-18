<!-- Main Header -->
        <header class="main-header">

            <!-- Logo -->
            <a class="logo">
                <!-- 小图标 -->
                <span class="logo-mini"><i class="fa fa-laptop"></i></span>
                <!-- 大图标 -->
                <span class="logo-lg"><i class="fa fa-laptop"></i>${pd.SYSNAME}</span>
            </a>

            <!-- Header Navbar -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <a class="sidebar-title" >${pd.SYSNAME}</a>
                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <!-- System Switch -->
                     <!--    <li class="dropdown system-switch">
                            <a href="map/showmap?sysName=onemap" class="dropdown-toggle"><i class="fa fa-map-o"></i>  一张图</a> 
                        </li> 
                        <li class="dropdown system-switch">
                            <a href="main/index?sysName=file" class="dropdown-toggle"><i class="fa fa-file"></i>  数字档案</a> 
                        </li> 
                        <li class="dropdown system-switch">
                            <a href="map/show3d?sysName=3d" class="dropdown-toggle"><i class="fa fa-globe"></i>  三维辅助</a> 
                        </li>                     --> 
                       
                        <!-- User Account Menu -->
                        <li class="dropdown user user-menu">
                            <!-- Menu Toggle Button -->
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <!-- The user image in the navbar-->
                                <img src="plugins/xha/dist/img/user2-160x160.png" class="user-image" alt="User Image">
                                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                <span id="loginName" class="hidden-xs">Admin</span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- The user image in the menu -->
                                <li class="user-header">
                                    <img src="plugins/xha/dist/img/user2-160x160.png" class="img-circle" alt="User Image">

                                    <p id="userInfo">
                                        Admin
                                        <small><i class="fa fa-circle text-success"></i> 超级管理员</small>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                 <!--    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">更改信息</a>
                                    </div> -->
                                    <div class="pull-right">
                                        <a href="logout" class="btn btn-default btn-flat">注销登录</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>