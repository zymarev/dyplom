<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns:c="http://www.w3.org/1999/html">
<head>
    <title>Registration page</title>

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet" />

    <script src="https://www.google.com/recaptcha/api.js"></script>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/registration/registration.js"> </script>
    <style>
        .fade.in {
            opacity: 1;
        }

        .modal.in .modal-dialog {
            -webkit-transform: translate(0, 0);
            -ms-transform: translate(0, 0);
            -o-transform: translate(0, 0);
            transform: translate(0, 0);
        }

        .modal-backdrop.in {
            opacity: 0.5;
        }
    </style>

</head>
<body>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
	<a class="navbar-brand" href="#">Payments</a>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
    		<ul class="mr-auto">
    		</ul>
    		<form class="form-inline my-2 my-lg-0">
    			<ul class="navbar-nav">
    				<li class="nav-item">
    				    <span class="nav-link">
    						<a class="nav-link" href="Controller.do?command=logout">Go to login</a>
    					</span>
    				</li>
    			</ul>
    		</form>
    	</div>
</nav>

<div class="container">
    <span>
        <p align="center"><font color="red">${errorMessage}</font></p>
    </span>

    <div class="row" style="padding-top: 3rem">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h4>Create new account</h4>
            <hr/>
            <form name="loginForm" action="Controller.do" method="post">
                <input type="hidden" name="command" value="register" />

                <div class="form-group ${errors.email ne null ? 'has-danger' : ''}">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                        <input type="email" class="form-control ${errors.email ne null ? 'form-control-danger' : ''}" name="email" id="email" placeholder="you@example.com"/>
                    </div>
                    <div class="form-control-feedback">${errors.email}</div>
                </div>

                <div class="form-group ${errors.password ne null ? 'has-danger' : ''}">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                        <input type="password" class="form-control ${errors.password ne null ? 'form-control-danger' : ''}" name="password" id="password" placeholder="Password"/>
                    </div>
                    <div class="form-control-feedback">${errors.password}</div>
                </div>

                <div class="form-group ${errors.firstName ne null ? 'has-danger' : ''}">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                        <input type="text" class="form-control ${errors.firstName ne null ? 'form-control-danger' : ''}" id="firstName" name="firstName" placeholder="First Name"/>
                    </div>
                    <div class="form-control-feedback">${errors.firstName}</div>
                </div>

                <div class="form-group ${errors.lastName ne null ? 'has-danger' : ''}">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                        <input type="text" class="form-control ${errors.lastName ne null ? 'form-control-danger' : ''}" id="lastName" name="lastName" placeholder="Last Name"/>
                    </div>
                    <div class="form-control-feedback">${errors.lastName}</div>

                    <div class="form-group ${errors.phoneNumber ne null ? 'has-danger' : ''}">
                                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                                            <input type="text" class="form-control ${errors.phoneNumber ne null ? 'form-control-danger' : ''}" name="phoneNumber" id="phoneNumber" placeholder="Phone"/>
                                        </div>
                                        <div class="form-control-feedback">${errors.phoneNumber}</div>


                                                        </div>
                </div>


                <span>
                    <button type="submit" class="btn btn-success">Create account</a>
                    <button type="button" class="btn btn-danger" onclick="location.href='index.jsp'">Cancel</button>
                </span>

            </div>
        </div>
    </form>
</div>
 <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="Modal1" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="ModalLabel">Error</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                        </div>
                        <div id="errorModalMessage" class="modal-body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
</div>
</body>
</html>
