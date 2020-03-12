var socket = null;
var stompClient = null;

var userAction = {
    init: function(){
        var _this = this;
        $('div.card-body').on('click', function(e){ // TODO: id, name 은 어떻게 전달하지 ?
            // GetContent("xxx");
            // alert(this.childElementCount);
            /* this.childElementCount */
            // alert(this.children[2].value); // product name ? ok
            /*
            product name:  this.children[0].innerHTML
            product price: this.children[1].innerHTML
            product list index: this.children[2].value
            */
            var productName = this.children[0].innerHTML;
            var productPrice = this.children[1].innerHTML;
            var productListIndex = this.children[2].value - 1;

            _this.click(productListIndex, productName);
        });

        $('#cart_btn').on('click', function (e) {
            //var productListIndex = this.children[2].value - 1;
            _this.click_cart();
        })

        this.connectAndSubscribe();
    },

    subscribeWSMessage: function(){
        var _this = this;
        console.log('userAction : subscribeMessage');

        stompClient.subscribe(
            '/queue/message',
            function(resultObj){
                console.log('subscribe callback : result' + resultObj);

                var result = JSON.parse(resultObj.body);
                // 첫번째 alert 는 왜 출력이 안되는걸까 ?
                _this.alert(result); // 받을 결과 일단 그대로 출력. 지연된 시간 알려면 어떻게 해야 할까? TODO
                _this.alert("!!");
            }
        );
    },

    connectAndSubscribe: function(){
        var _this = this;
        console.log('userAction : connectAndSubscribe');

        if(stompClient == null ||
            !stompClient.connected){ // 연결 준비x

            socket = new SockJS('/websocket');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function(frame){
                // alert("소켓 연결 성공"+ frame);
                _this.subscribeWSMessage();
            });

        } /*else{ // 연결 준비o // TODO 웹 소켓 연결이 반복되면, 새로운 연결 -> @SendToUser 로는 못받나 ? oo맞아
            this.subscribeWSMessage();
        }*/
    },


    click: function(id, itemName){
        console.log('userAction : click');

        var data = {
            actionType: 'click',
            time: new Date().toString(),
            item: itemName
        };

        stompClient.send(
            '/action', {},
            JSON.stringify(data)
        );

        window.location.href='/products/'+id; // TODO : 화면 이동 없이 구독 콜백 확인해보자
    },

    click_cart : function () {

        var data = {
            productName: $('#cart_productName')

        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/products/cart',

            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function(){
            alert('장바구니에 상품이 담겼습니다');
            window.location.href='/products/cart';
        }).fail(function(error){
            //window.location.href='/';
        });
    }

};

userAction.init();