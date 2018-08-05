<%-- include header file --%> 
<%@include file="header.jsp" %>

<section id="form"><!--form-->
    <div class="container">
        <div class="row">
            <div class="alert alert-warning" role="alert" id="error" style="display: none;"></div>
            <div class="col-sm-4 col-sm-offset-1">
                <div class="login-form"><!--login form-->
                    <h2>Login to your account</h2>
                    <form action="Signin" id="SigninForm" method="post">
                        <input type="text" class="form-control" placeholder="username" id="SigninName" name="SigninName" required/>
                        <input type="password" class="form-control" placeholder="password" id="SigninPassword" name="SigninPassword" required/>
                        <button type="submit"  class="btn btn-primary">Login</button>
                    </form>
                </div><!--/login form-->
            </div>
            <div class="col-sm-1">
                <h2 class="LoginORSignUp">OR</h2>
            </div>
            <div class="col-sm-4">
                <div class="signup-form"><!--sign up form-->
                    <h2>New User Signup!</h2>
                    <form action="Signup" method="post" id="SignupForm" onsubmit="return validateSignup();">
                        <input type="text" class="form-control" placeholder="Name" id="SignupName" name=SignupName required/>
                        <input type="email" class="form-control" placeholder="Email Address" id="SignupEmail" name=SignupEmail required/>
                        <input type="password" class="form-control" placeholder="Password" id="SignupPassword" name=SignupPassword required/>
                        <input type="password" class="form-control" placeholder="ConfirmPassword" id="SignupConfirmPassword" required/>


                        <input type="text" class="form-control" placeholder="Address" id="SignupAddress" name="SignupAddress" required/>
                        <input type="text" class="form-control" placeholder="Job" id="SignupJob" name="SignupJob" required/>
                        


                        <!--validate password-->
                        <button type="submit"  class="btn btn-primary">Signup</button>
                    </form>
                </div><!--/sign up form-->
            </div>
        </div>
    </div>
</section><!--/form-->

<%-- include footer file --%> 
<%@include file="footer.jsp" %>