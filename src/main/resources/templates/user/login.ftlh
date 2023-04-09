<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Booking</title>
</head>
<body>


<main class="text-center d-flex justify-content-center align-items-center view">
    <form method="post" action="/login" style="width: 400px">



        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

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



        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>

        <a href="users/new">Registration</a>
    </form>
</main>

</body>
</html>
