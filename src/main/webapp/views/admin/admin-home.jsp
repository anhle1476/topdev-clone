<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="include/baseHeader.jsp"></c:import>
    <title>Dashboard - Topdev CLONE</title>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <c:import url="include/navbar.jsp"/>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <c:import url="include/sidebar.jsp"></c:import>

    <c:set var="user" value='${sessionScope["user"]}'/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Tài khoản</h1>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
            <!-- Default box -->
            <div class="card card-solid">
                <div class="card-body pb-0">
                    <div class="row d-flex align-items-stretch">
                        <div class="col-12 col-sm-6">
                            <div class="card card-info">
                                <div class="card-header">
                                    <h3 class="card-title">Thông tin</h3>
                                </div>
                                <div class="card-body">
                                    <h2 class="lead mb-3"><b>${user.name}</b></h2>
                                    <ul class="ml-4 fa-ul text-muted">
                                        <li class="my-3">
                          <span class="fa-li"
                          ><i class="fas fa-puzzle-piece"></i
                          ></span>
                                            Vị trí: ${user.role.name}
                                        </li>
                                        <li class="my-3">
                          <span class="fa-li"
                          ><i class="fas fa-envelope"></i
                          ></span>
                                            Email: ${user.email}
                                        </li>
                                        <li class="my-3">
                                            <span class="fa-li"><i class="fas fa-copy"></i></span>
                                            Bài viết: ${requestScope["postStatus"]}
                                        </li>
                                    </ul>
                                </div>
                                <div class="card-footer">
                                    <div class="text-right">
                                        <a href="#" class="btn btn-info">
                                            <i class="fas fa-sign-out-alt"></i> Đăng xuất
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-sm-6">
                            <div class="card card-info my-0">
                                <div class="card-header">
                                    <h3 class="card-title">Đổi mật khẩu</h3>
                                </div>
                                <!-- /.card-header -->
                                <!-- Change password form start -->
                                <form>
                                    <div class="card-body">
                                        <div class="form-group row mb-1">
                                            <label for="current-password" class="col-form-label"
                                            >Mật khẩu hiện tại</label
                                            >
                                            <input
                                                    type="password"
                                                    class="form-control"
                                                    id="current-password"
                                                    placeholder="Current Password"
                                                    required
                                            />
                                        </div>
                                        <div class="form-group row mb-1">
                                            <label for="new-password" class="col-form-label"
                                            >Mật khẩu mới</label
                                            >
                                            <input
                                                    type="email"
                                                    class="form-control"
                                                    id="new-password"
                                                    placeholder="New Password"
                                                    required
                                            />
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-info float-right">
                                            <i class="fas fa-check"></i> Xác nhận
                                        </button>
                                    </div>
                                    <!-- /.card-footer -->
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <c:import url="include/footer.jsp"></c:import>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<c:import url="include/baseJs.jsp"></c:import>
</body>
</html>