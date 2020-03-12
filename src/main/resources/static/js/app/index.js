var index = {
    init : function(){
        var _this = this;
        $('#btn-search').on('click', function(){
            _this.search();
        });

    },

    addCart:function(){
        $('#btn-cart').on('click', function(){
            this.addCart();
        });
        console.log("Add Cart");
    },

    search : function(){ // TODO ?keyword={keyword} 로 변경하기
        var keyword = $('#keyword').val();
        // console.log('\n'+keyword+'\n'); 콘솔에 출력안되는듯!

        // $.get( '/api/v1/products/'+data.keyword,{},function(data,status){
        //     alert('결과'+status);
        //     $('#keyword').val('');
        //     window.location.href='/'
        // });

        $.ajax({
            type: 'GET',
            url: '/api/v1/products/'+keyword,

            dataType: 'json',
            contentType: 'application/json; charset=utf-8'

        }).done(function(){
            alert('검색 요청 완료');
            window.location.href='/' // TODO: reload 를 내가 한 게 아닐까 ? 근데 reload 안하면 출력안되는데..
        }).fail(function(error){
            // alert('검색 요청 실패\n'+error.toString()+'\n'+error.);
            // TODO; 잘됐는데 실패라고 하는 이유가 뭐지 ?
            window.location.href='/'
        });
    }
};

index.init();