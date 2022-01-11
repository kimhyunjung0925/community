{
    const idRegex = /^([a-zA-Z0-9]{4,15})$/; //대소문자+숫자 조합으로 4~15글자인 경우만 ok
    const pwRegex = /^([a-zA-Z0-9!@_]{4,20})$/; //대소문자+숫자+!@_ 조합으로 4~20글자인 경우만 ok
    const nmRegex = /^([가-힣]{2,5})$/; //한글로만 2~5글자인 경우만
    const msg1 = '아이디는 대소문자, 숫자 조합으로 4~15글자가 되어야 합니다.';

    let idChkState = 2; // 0:아이디 사용 불가, 1:아이디 사용 가능. 2:아이디 체크 안함

    const joinFrmElem = document.querySelector('#join-frm');

    //중복확인 버튼 클릭시 0이면(이미 아이디가있는 값),1이면(아이디 없는 값) 일 때 설정
    const setIdChkMsg = (data) => {
        idChkState = data.result; // 0 or 1
        const idChkMsgElem = joinFrmElem.querySelector("#id-chk-msg");
        switch (data.result){
            case 0:
                idChkMsgElem.innerText = '이미 사용중인 아이디 입니다.'
                break;
            case 1:
                idChkMsgElem.innerText = '사용할 수 있는 아이디 입니다.'
                break;
        }
    }
    
    if(joinFrmElem){
        //아이디 중복체크 버튼눌렀을 때 아이디 형식에 맞지 않았을 때
        const idBtnChkElem = joinFrmElem.querySelector('#id-btn-chk');
        idBtnChkElem.addEventListener('click',() => {
           const idVal = joinFrmElem.uid.value; //string
            if(!idRegex.test(idVal)){
                alert(msg1)
                return;
            }
           if(idVal.length < 4) {
               alert('아이디는 4자 이상 작성해 주세요.')
               return;
           }

           //리터럴 템플릿(문자열 안에 변수 넣기 편함)
           fetch(`/user/idChk/${idVal}`)
           .then(res => res.json())
           .then((data) => {
               setIdChkMsg(data);
           }).catch((e)=> {
               console.log(e);
           });
        });

        //------------------submit 눌렀을 때------------------//

        //submit(join)했을 때 아이디,비밀번호,이름 형식에 맞게 쳤는지 확인하고 알러트
        joinFrmElem.addEventListener('submit', (e) => {
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const upwChk = joinFrmElem.querySelector('#upw-chk').value;
            const nm = joinFrmElem.nm.value;
            if(!idRegex.test(uid)){
                alert(msg1);
                e.preventDefault();
            } else if (!pwRegex.test(upw)){
                alert('비밀번호는 대소문자, 숫자, 특수문자(!@_) 조합으로 4~20글자가 되어야 합니다.');
                e.preventDefault();
            }else if(upw !== upwChk){
                alert('비밀번호와 체크비밀번호를 확인해 주세요.');
                e.preventDefault();
            } else if(!nmRegex.test(nm)){
                alert('이름은 한글로 2~5글자가 되어야 합니다.');
                e.preventDefault();
            }

            //아이디 중복확인 안하고 submit 눌렀을 때
            if (idChkState !== 1) {
                switch (idChkState) {
                    case 0:
                        alert("다른 아이디를 사용 해 주세요")
                        break;
                    case 2:
                        alert("아이디 중복확인을 해 주세요.")
                        break;
                }
                e.preventDefault();
            }
        });

        //중복확 인 해놓고 아이디 변경했을 때
        joinFrmElem.uid.addEventListener('keyup' , ()=>{
            const idChkMsgElem = joinFrmElem.querySelector('#id-chk-msg');
            idChkMsgElem.innerText = '';
            idChkState = 2;
        });
    }
}


//innertext, innerHtml이랑 차이!!!
