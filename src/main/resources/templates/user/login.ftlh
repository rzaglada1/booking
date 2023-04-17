<#import '../macros/head_footer_style.ftlh' as hs>

<@hs.headStyle>




<main class="text-center bg-dark d-flex justify-content-center align-items-center view">
    <form method="post" action="/login" style="width: 400px">



        <h1 class="h3 mb-3 fw-normal text-light">Please sign in</h1>

        <div class="form-floating mb-2">
            <input id ="floatingEmail" type="text" maxlength="50" class="form-control" placeholder="Email"
                   name="username" >
            <label for="floatingEmail">Email</label>


        </div>

        <div class="form-floating mb-2">
            <input id ="floatingPassword" type="password"  maxlength="50" class="form-control" placeholder="Password"
                   name="password" >
            <label for="floatingPassword">Password</label>
        </div>



        <button class="w-100 btn btn-lg btn-warning" type="submit">Sign in</button>

        <a href="users/new">Registration</a>
    </form>
</main>

</@hs.headStyle>

