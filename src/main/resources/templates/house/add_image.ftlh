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


<main class=" text-center d-flex justify-content-center align-items-center view">
    <div class="container col-14 d-flex align-items-center justify-content-center">
        <form method="post" action="/images" enctype="multipart/form-data" class="w-75">

            <br>

            <div class="form-group">
                <label for="file"><b>Зображення продукту</b></label>
                <input name="file" type="file" accept=".png, .jpg, .jpeg" class="form-control">
            </div>

            <br>


            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success"><b>Зберегти</b></button>
                <a href="" class="btn btn-danger"><b>Відмінити</b></a>
            </div>
        </form>
    </div>
</main>

    <script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.umd.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</body>
</html>