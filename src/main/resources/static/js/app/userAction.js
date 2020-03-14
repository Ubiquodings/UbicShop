var socket = null;
var stompClient = null;

var userAction = {
    init: function(){
        const _this = this;

        $("#btn-shoplist").on('click',function(e){ // detail 페이지에서 가져와야지
            var data = {
                type: 'shoplist',
                // time: new Date().toString(),
                productName: $('#item-name').val()//itemName
            };
            // console.log($('#item-name').val()); // ok
            $.ajax({
                type: 'POST',
                url: '/api/v1/kafka',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            });
            window.location.href='/';
        });
        $("#btn-order").on('click',function(e){
            var data = {
                type: 'order',
                // time: new Date().toString(),
                productName: $('#item-name').val()
            };
            $.ajax({
                type: 'POST',
                url: '/api/v1/kafka',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            });
            window.location.href='/';
        });

        $('div.card-body').on('click', function(e){ // TODO: id, name 은 어떻게 전달하지 ?
            /*
            product name:  this.children[0].innerHTML
            product price: this.children[1].innerHTML
            product list index: this.children[2].value
            */
            var productName = this.children[0].innerHTML;
            var productPrice = this.children[1].innerHTML;
            var productListIndex = this.children[2].value;// 상품 하드코딩하니까.. - 1;

            _this.click(productListIndex, productName);
        });

        setTimeout(function() {
            console.log('Works!');
            _this.connectAndSubscribe();
        }, 4000); // TODO -- 구글 로그인이랑 웹소켓 연결이랑 동시에 실행되면서 인증 리다이렉트 제어 안된다
        // this.connectAndSubscribe();
    },

    subscribeWSMessage: function(){
        const _this = this;
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
            type: 'click',
            // time: new Date().toString(),
            productName: itemName
        };
        //
        // stompClient.send(
        //     '/action', {},
        //     JSON.stringify(data)
        // );
        $.ajax({
            type: 'POST',
            url: '/api/v1/kafka',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        });


            // 페이지 이동
        window.location.href='/products/'+id; // TODO : 화면 이동 없이 구독 콜백 확인해보자
    }

};

userAction.init();