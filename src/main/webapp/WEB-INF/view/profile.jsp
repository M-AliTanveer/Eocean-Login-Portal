<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="resources/css/profile.css">
</head>
<body>
<div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="boxing row d-flex justify-content-center">
<div class="col-xl-6 col-md-12">
                                                <div class="card user-card-full">
                                                    <div class="block row m-l-0 m-r-0">
                                                        <div class="col-sm-4 bg-c-lite-green user-profile">
                                                            <div class="card-block text-center text-white" id="heightcontainer">
                                                                <div class="m-b-25">
                                                                    <img src="data:image/jpg;base64,${profilepicture}" width="120" height="120" class="img-radius" alt="User-Profile-Image">
                                                                </div>
                                                                <h3 id="nameheading" class="f-w-600" >${fullname}</h3>
                                                                <p>Student Account</p>
                                                                </div>
                                                        <div class="empty"></div>
                                                                <input type="button" id="logout" value = "Logout" onclick="location.href='logout'">
                                                            </div>
                                                        <div class="col-sm-8">
                                                            <div class="card-block">
                                                                <h6 class="m-b-20 p-b-5 b-b-default f-w-600">Personal Information</h6>
                                                                <div class="row">
                                                                    <div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Full Name</p>
                                                                        <h6 class="text-muted f-w-400">${fullname}</h6>
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Email</p>
                                                                        <h6 class="text-muted f-w-400">${email}</h6>
                                                                    </div>
                                                                <div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Date of Birth</p>
                                                                        <h6 class="text-muted f-w-400">${DOB}</h6>
                                                                    </div><div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Gender</p>
                                                                        <h6 class="text-muted f-w-400">${gender}</h6>
                                                                    </div><div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Batch</p>
                                                                        <h6 class="text-muted f-w-400">${batch }</h6>
                                                                    </div><div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Enrolled Program</p>
                                                                        <h6 class="text-muted f-w-400">${program }</h6>
                                                                    </div></div>
                                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Projects</h6>
                                                                <div class="row">
                                                                    <div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Recent</p>
                                                                        <h6 class="text-muted f-w-400">Sam Disuja</h6>
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                        <p class="m-b-10 f-w-600">Most Viewed</p>
                                                                        <h6 class="text-muted f-w-400">Dinoter husainm</h6>
                                                                    </div>
                                                                </div>
                                                                <ul class="social-link list-unstyled m-t-40 m-b-10">
                                                                    <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="facebook" data-abc="true"><i class="mdi mdi-facebook feather icon-facebook facebook" aria-hidden="true"></i></a></li>
                                                                    <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="twitter" data-abc="true"><i class="mdi mdi-twitter feather icon-twitter twitter" aria-hidden="true"></i></a></li>
                                                                    <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="instagram" data-abc="true"><i class="mdi mdi-instagram feather icon-instagram instagram" aria-hidden="true"></i></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                             </div>
                                                </div>
                                            </div>

</body>
</html>