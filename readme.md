## 💡  Topic

- **장비 상세 모니터링을 위한 서비스**

## 📝 Summary

모니터링 대상 장비에 대해 전체, 장비 그룹, 장비 OS, 서비스 상태의 관점 별로 현황 모니터링을 제공하며 전체 장비의 상세정보를 리스트 형태로 제공 및 차트로 볼 수 있다.

## ⭐️ Key Function

- 상세 모니터링
    - 장비에 대한 전체 프로세스, CPU, Memory, Network, Nic 모니터링
    - 단위(bps, kbps, Mbps, Gbps) 별로 조회
    - 차트(bar, line) 형태 제공
    - 자원, 장비, 날짜에 따른 조건 조회
    - Highchart 라이브러리
- 장비 구성도 파악
    - 사용자 원하는 지도, 건물별 층 등등 배경이미지 넣어서 장비 구성
    - goJS 라이브러리
- 장비에 대한 전체 그룹, 개별 그룹 관리
    - 전체 장비의 상세정보를 리스트 형태로 제공
    - H/W 값을 즉시 변경 가능
    - 장비 타입과 유형에 따른 조건 조회
    - Rc-tree 라이브러리
- 로그 조회 기능
    - 사용자가 발생한 action 기준에 따른 데이터 저장
    - 사용자, 작업 구분 및 날짜에 따른 조건 조회
    - AG-Gird 라이브러리
- PDF, Excel 출력
    - PDF 읽기 전용 출력
    - Excel 데이터 조회 및 방대한 데이터 삽입 가능
- 회원 관리 기능(JWT 토큰)
    - 회원 토큰 - 패스워드 뺀 나머지 주요 컬럼들만 인코딩
    - 지정된 시간 만료되면 로그인 페이지로 이동
    - 로그아웃 - 토큰 사라짐

## ****🛠**** Tool

- React
- TypeScript
- SpringBoot
- Java
- PostgreSQL
- Postman

# 🤔 Learned

- **MVC** 패턴을 처음으로 도입해보며, **패턴에 대한 이해**를 높일 수 있었음
- `IoC`을 통해 **DI**를 처음으로 적용해보며, **의존성 주입의 편리함**을 깨닫게 되었음
- `ORM`를 통해 N+1문제와 객체와 DB의 매핑에 대해 더 자세히 알게 되었음
- 무한의 Depth를 위해서 `Recursion` 알고리즘을 써보게 되었음
- `Scroll Event`를 통해 데이터 핸들링을 해보았음
- `byte`단위 데이터를 B/s, KB/s, MB/s, GB/s 사람이 알아볼 수 있는 단위로 변경
- `PDF`, `Excel` 파일 출력을 서버에서 라이브러리를 통해서 구현


## ⭐️ 문제점 및 해결

- **전체 차트 렌더링**

차트의 유형을 바꾸거나 단위를 변경하면 모든 차트들이 렌더링이 되는 문제점이 있었다. 차트의 옵션(유형,단위)과 데이터를 한 개의 컴포넌트에 다 넣어서 옵션이 변경될 때마다 전체 렌더링이 되었다. 그래서 옵션 컴포넌트와 데이터 컴포넌트를 나누어 주었다. 만약 옵션이 변경되면 React componentDidUpdate( ) 메서드와 D3.js update( ) 활용해서 바뀐 차트만 업데이트를 해주면 한 개의 차트만 옵션이 바꾸게 된다.

- **관리자 페이지**

웹에서 관리자 페이지를 노출시키지 않기 위해서 삼항 연산자를 사용해서 뜨지 않게 했지만 url 입력했을 때 페이지가 뜨고 API 통신이 되는 문제점이 있었다. 서버에서는 @PreAuthorize 어노테이션을 활용해서 호출할 관리자 권한이 없으면 API 통신이 할 수 없게 막아두고 클라이언트에서는 라우터를 설정할 때 삼항 연산자를 활용해서 권한이 있으면 관리자 페이지 없으면 로그인 페이지로 갈 수 있게 해두었다. 

- **출력 기능은 서버**

파일을 출력할 때 처리하는 방식은 클라이언트 vs 서버에서 2가지 처리 방식이 있다.

1. **jspdf + react-vis(chart) 클라이언트 처리**
렌더링(5~10초) 걸리는 문제점이 있었다.
2. **@react-pdf/renderer + recharts 클라이언트 처리**
이것도 Image 변환하는 방식이다. 하지만 렌더링이 너무 느리다.
3. **iText + JFreeChart 서버 처리**
클라이언트 데이터를 서버에 넘겨서 가공해서 처리하는 방식으로, 
렌더링이 아주 빠르다는 장점이 있다.

클라이언트에서 렌더링하여 PDF 출력할 때 데이터가 크거나 페이지가 많아질수록 출력하는 시간이 길어지는 문제점이 있었다. 클라이언트에서 처리하면 사용자의 CPU, Memory 사양에 따라 달라진다는 것을 깨달았다. 렌더링의 우선 순위를 보았을 때 **iText + JFreeChart** 아주 우수하기 때문에 이 방법을 선택했다.

- **다양한 라이브러리 및 역라이브러리 구현**

Bootstrap, AG-Gird, goJS, Rc-tree, Highchart, POI, jfreechart, orsonpdf 등 라이브러리를 통해 기능들을 만들 수 있었다. 라이브러리를 검색하고 시각적으로 대충 어떤 기능을 하는지 보고 다운받아서 쓰면 나중에 요구사항에 맞는 기능이 없는 문제점이 생겼다. 라이브러리를 찾는 것도 중요하지만 요구사항에 맞는 API를 제공하는지 튜토리얼은 잘 구성되어 있는지 만약 그 기능이 없으면 내가 코드를 작성할 수 있는 기능인지 생각한 다음 라이브러리를 써야한다는 것을 깨달았다. 그러면 삽질하는 시간이 줄어들고 생산성이 증가하기 때문이다. 기존 제품에는 Z-tree 라이브러리를 통해 만들었지만 나는 Rc-tree를 통해 충분한 기능들을 만들 수 있었다.

# ⚙️ ERD
![image](https://github.com/coorr/Algorithm/blob/main/img/aiwacs_ERD.drawio.png)

# 📷 Screenshot
![image](https://github.com/coorr/Algorithm/blob/main/img/report.png)
![image](https://github.com/coorr/Algorithm/blob/main/img/equipment_manage.png)
![image](https://github.com/coorr/Algorithm/blob/main/img/equipment_group_manage.png)
![image](https://github.com/coorr/Algorithm/blob/main/img/history_record.png)
![image](https://github.com/coorr/Algorithm/blob/main/img/topology.png)
![image](https://github.com/coorr/Algorithm/blob/main/img/file_print.png)
![image](https://github.com/coorr/Algorithm/blob/main/img/record_gif.gif)
