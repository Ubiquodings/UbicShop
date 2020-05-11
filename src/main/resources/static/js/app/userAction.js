var userAction = {
    init: function(){
        var _this = this;

        $("#btn-order-cancel").on('click',function(e){ // detail 페이지에서 가져와야지
            $.ajax({
                type: 'DELETE',
                url: '/orders/'+ $("input#input-order-id").val(), // order id
                dataType: 'json',// Accept ?
                contentType:'application/json; charset=utf-8',
                // data: JSON.stringify({}) // TODO 간단하게 url param 으로 대체
            }).done(function() {
                alert('삭제되었습니다.');
                window.location.href=window.location.href;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
            // .done(function(){ // 왜 안되지 ?
            //     alert('ok');
            // });
            // window.location.href=window.location.href;
        });

        $("#btn-shoplist").on('click',function(e){ // detail 페이지에서 가져와야지
            $.ajax({
                type: 'POST',
                url: '/carts/new/'+ $("input#product-detail-id").val(),
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify({}) // TODO 간단하게 url param 으로 대체
            }).done(function(){ // 왜 안되지 ?
                alert('ok');
            });
            // window.location.href=window.location.href; // reloading ?
        });
        $("#btn-cart-order").on('click', function(e){ // detail 페이지에서 가져와야지
            // console.log('ajax 전');
            $.ajax({
                type: 'POST',
                url: '/orders/new/'+ $("input#input-cart-order").val(),
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify({}) // TODO 간단하게 url param 으로 대체
            }).done(function(){ // 왜 안되지 ?
                alert('ok');
            });
            // console.log('ajax 후');
            // window.location.href=window.location.href; // reloading ?
        });

        $("#btn-order").on('click',function(e){
            var data = {};
            $.ajax({
                type: 'POST',
                url: '/orders/new/'+ $("input#product-detail-id").val(),
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            });
            // window.location.href='/';
        });

        $('div#product-list-card-body').on('click', function(e){ // TODO: id, name 은 어떻게 전달하지 ?

            /*
            product name:  this.children[0].innerHTML
            product price: this.children[1].innerHTML
            product list index: this.children[2].value
            */
            var productName = this.children[0].innerHTML;
            var productPrice = this.children[1].innerHTML;
            // var productListIndex = this.children[2].value;// 상품 하드코딩하니까.. - 1;
            var productId = this.children[2].value;
            // _this.click(productId, productName);
            _this.clickItem(productId);
        });

    },
    clickItem:function(id){
        window.location.href = '/products/'+id;
    }
};

userAction.init();