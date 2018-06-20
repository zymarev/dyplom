<html>

<head>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/font-awesome.css" />

    <script src="https://www.google.com/recaptcha/api.js"></script>
    <title>Payment service</title>
</head>

<body>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
	<a class="navbar-brand" href="#">Diplom</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
        		<ul class="mr-auto">
        		</ul>
        		<form class="form-inline my-2 my-lg-0">
        			<ul class="navbar-nav">
        				<li class="nav-item">

        				</li>
        			</ul>
        		</form>
        	</div>
	</nav>
<div class="container" align="center" style="background-color: #F9F9F2">
    <div class="page-header">

        <h2>${successRegistration}</h2>
    </div>
</div>

<div class="container">
    <span>
        <p align="center"><font color="red">${errorMessage}</font></p>
    </span>

    <div class="row" style="padding-top: 3rem">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h4>Log in with your credentials</h4>
            <hr/>
            <form name="loginForm" action="Controller.do" method="post">

                <input type="hidden" name="command" value="login"/>
                <div class="form-group">
                    <label class="sr-only" for="email">E-Mail Address</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                        <input type="text" name="email" class="form-control" id="email" placeholder="you@example.com" required autofocus>
                    </div>
                </div>

                <div class="form-group">
                    <label class="sr-only" for="password">Password</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                        <input type="password" name="password" class="form-control" id="password" placeholder="Password" required />
                    </div>
                </div>

                <button type="submit" name="submit" class="btn btn-success"><i class="fa fa-sign-in"></i> Login</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>