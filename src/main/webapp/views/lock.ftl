<!DOCTYPE html>
<html lang="en-us" id="lock-page">

<@lib.layoutHead>
<!-- page related CSS -->
<link rel="stylesheet" type="text/css" media="screen" href="/assets/css/lockscreen.min.css">
</@lib.layoutHead>

<body>

<div id="main" role="main">

    <!-- MAIN CONTENT -->

    <form class="lockscreen animated flipInY" method="post" action="/login">
        <div class="logo">
            <h1 class="semi-bold"><img src="/assets/img/logo-o.png" alt=""/> SmartAdmin</h1>
        </div>
        <div>
            <img src="/assets/img/avatars/sunny-big.png" alt="" width="120" height="120"/>
            <div>
                <h1><i class="fa fa-user fa-3x text-muted air air-top-right hidden-mobile"></i>
                    ${user.username}
                    <small><i class="fa fa-lock text-muted"></i> &nbsp;Locked</small>
                </h1>
                <p class="text-muted">
                    <a href="mailto:${user.email}">${user.email}</a>
                </p>

                <div class="input-group">
                    <input type="hidden" name="username" value="${user.username}">
                    <input class="form-control" type="password" name="password" placeholder="Password">
                    <div class="input-group-btn">
                        <button class="btn btn-primary" type="submit">
                            <i class="fa fa-key"></i>
                        </button>
                    </div>
                </div>
                <p class="no-margin margin-top-5">
                    使用其他账户登录? <a href="/"> 点击此处</a>
                </p>
            </div>

        </div>
        <p class="font-xs margin-top-5">
            Copyright Mercury 2014-2020.

        </p>
    </form>

</div>

<!--================================================== -->
<@lib.javascriptBase></@lib.javascriptBase>

</body>
</html>