package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppDatabase {
    private final static int DIVIDED =7;
    private static Context context;
    private static PackageManager pm;
    private static List<ResolveInfo> pkgAppList;
    private static ArrayList<AppInfo> app_info_list;

    final static String[] type_tags = {"건강/운동","교육","금융","뉴스/잡지","도구","도서","라이프스타일","생산성","소셜","식음료","엔터테인먼트","여행","지도/교통","쇼핑","커뮤니케이션","기타및게임"};
    final static String[] color_tags={"빨강","주황","노랑","초록","파랑","보라","무채색"};

    // "건강/운동","교육","금융","뉴스/잡지","도구","도서","라이프스타일","생산성","소셜","식음료","엔터테인먼트","여행","지도","쇼핑","커뮤니케이션","기타"
    final static String[][] category_dict={
            {"모바일 건강보험증", "The건강보험", "손목닥터9988", "캐시워크", "비트버니", "만보기", "야핏무브", "만보기 어플", "Health Tracker", "BP Tracker", "머니워크", "혈압기록계-혈압관리", "CAZZLE 캐즐", "복지로", "Mi Fitness", "워크온", "혈압 앱", "InBody", "닥터다이어리", "젠톡", "런데이", "스텝 트래커", "인아웃", "발로소득", "덴티아이경기", "마인드카페", "Nike Run Club", "만보기", "세라젬", "국군복지포털", "필라이즈", "앳플리", "만보기", "혈압", "파라리움", "혈당", "파스타", "모두닥", "건강e음", "혈당 & 혈압 기록계", "달다방", "덴티아이", "트랭글", "힐리", "마통","헬스","피트니스","다짐","운동닥터","플릭"},//건강,운동
            {"숭실대학교 도서관","모바일 ID", "LearningX Student","Q-Net","공감영단어","듀오링고","네이버 사전","말해보카","고용노동부 HRD-NET", "나이스학부모서비스","클래스카드","HeyJapan","콴다","PictureThis","저절로암기 한자","스픽","똑똑보카","하이클래스","아이들나라","리로스쿨","나이스플러스","워드빗 영어","Stellarium Mobile","저절로암기 영단어","Classroom","Ai펭톡","Praktika","도깨비 전화","운전면허 PLUS","열품타","쥬니버스쿨","Plant-X","Cake","부모나라","생활영어 첫걸음","Simply Piano","랠리즈","왕초보 생활영어", "HelloTalk","메모리워드","코참패스","한국장학재단","Lingutown","워드빗 일본어","레슨북","메가스터디","EBSi 고교강의","식물 식별자","EBS 중학"},//교육
            { "삼쩜삼", "monimo", "PAYCO","토스", "서울Pay+", "카카오페이", "NH pay", "네이버페이", "KB Pay", "케이뱅크", "신한 슈퍼SOL", "신한은행","NH올원뱅크", "신한 SOL페이", "하나Pay", "하나은행, 하나원큐", "카카오뱅크", "현대카드", "삼성카드", "KB스타뱅킹", "NH스마트뱅킹", "스마트 위택스", "우리WON뱅킹", "NH콕뱅크", "디지로카", "신한 SOL뱅크", "페이북/ISP", "모바일지로", "PASS by U+", "PASS by SKT", "KB손해보험+다이렉트", "i-ONE Bank", "NH농협카드 스마트앱", "트래블윌렛", "KB증권 ‘M-able'", "터치엔 엠백신(기업용)", "현대해상", "내 곁에 국민연금", "우리WON카드", "DB손해보험", "키움증권 영운문S", "M-STOCK", "MG더뱅킹", "현대백화점", "뱅크페이", "핀다", "아이부자","모바일\n티머니","대신 크레온","업비트","Travel Wallet","차이"},//금융
            {"현지뉴스", "한국 속보", "뉴닉", "한국 뉴스 속보", "Microsoft Start", "현대카드 DIVE", "가톨릭", "중앙일보", "조선일보", "MBN 매일방송", "팟 캐스트 라디오 음악", "KBS 뉴스", "더불어민주당", "CNN Breaking US & World News", "매일경제 Mobile", "YTN", "뉴스 속보", "실검 뉴스", "Google 뉴스", "MBC 뉴스", "모바일한경", "BBC", "Medium", "The New Tork Times", "뉴스플러스", "동아일보", "한국경제", "YTN 라디오", "연합뉴스", "KNN", "제주오일장신문 모바일앱", "현지뉴스", "매경e신문", "한국경제TV", "내 손안에 서울", "The Economist", "Bloomberg", "스타뉴스", "SBS 뉴스", "Filpboard", "연합뉴스 TV", "ArirangTV", "Quora", "한겨네", "뉴밍"},//뉴스잡지
            {"포토","모바일 신분증","갤러리","설정","보안 폴더","SetEdit","음성 녹음","하우머치","인터벌 타이머","날씨날씨","국세청 손택스", "Papago", "Smart Switch", "오토마우스", "V3 Mobile Plus", "PDF 리더", "Google 번역", "시티즌코난", "Cyber Cleaner", "모든 언어 번역", "유니콘 HTTPS", "모두의응원 LED 전광판", "인증자 앱", "SparkCleaner", "오토마우스", "ios로 이동", "Droid-X III 백신 (기업용)", "번역기", "무음찰칵", "QR과 바코드 스캐너", "SEIO Agent", "QR 스캐너", "노래방 노래검색", "디지털 나침반", "모든 동영상 다운로더 및 플레이어", "TEC 클리너", "메모", "V3 Mobile Security", "Google 렌즈", "TV용 리모컨", "Google Authenticator", "Auto Clicker", "Google 내 기기 찾기", "TeraBox클라우드", "WDownloader", "전광판 LED Scroller", "QR 스캐너 플러스", "음성 번역기", "QCY", "디자인키보드", "알약M", "U+ USIM", "Google Family Link", "박수를 쳐서 전화기를 찾으세요", "Bing","AhnLab V3 Mobile Plus","모바일 팩스","디지털원패스"},//도구
            {"Play 북", "밀리의 서재", "SERIES", "탭북", "교보문고", "예스24 도서 서점", "Joyread", "GoodNovel", "알라딘 인터넷서점", "성경", "버블탭", "교보eBook", "Valking.gg", "예스24 eBook", "성경일독Q", "성경: 큰글 성경", "DeepL 번역", "알라딘 전자책", "윌라", "블라이스", "매일 성경", "다번역 성경찬송 GOODTV", "사락", "문피아", "Webfic", "예스 24 전자도서관", "채티", "전자책+도서관정보", "가톨릭성경일독Q", "웹소설 노벨피아", "성경 + 오디오", "저절로 성경일독", "갓피플성경", "부커스", "공수달력", "네이트", "Google Play 북", "성경과찬송", "스토리텔", "초원", "개역개정판 큰글성경", "성경과 찬송가", "바이블25 성경 찬송", "북적북적", "북큐브 전자도서관","리브로피아"},//도서
            {"NAVER","우리동네GS", "Yessi", "에이닷", "다이소몰", "포켓CU", "ICN SMARTPASS", "LG ThinQ", "숨고", "H.Point", "아파트아이", "Pinterest", "T world", "Google Home", "마이 홈플러스", "인터넷등기소", "현대 블루링크", "U+멤버십", "런드리고", "너겟", "투썸하트", "이마트", "슈퍼로찌 슈퍼앱", "TP-Link Tapo", "Kia Connect", "점신", "아웃백", "퍼니지", "이멤버", "V컬러링", "마일벌스", "복지알리미", "아파트너", "TheDayBefore", "PASS by KT", "형사사법포털", "해피포인트", "이마트24", "포동", "CJ대한통운 택배", "실시간 TV", "미소", "MyNB", "고용보험 모바일","화해","마음의달인","알바몬"},//라이프스타일
            {"Adobe Scan","Polaris Office","OneDrive","Drive","문서","내 파일","카메라","캘린더","시계","계산기","ChatGPT", "Docx 리더", "PDF 리더", "챗봇 AI", "K-패스", "정부24", "모든 언어 번역기", "Microsoft Lens", "Google Gemini", "ChatOn", "문서뷰어", "한컴독스", "스프레드시트", "폴라리스 오피스", "QR & 바코드 스캐너", "Microsoft Copilot", "PDF 뷰어", "네이버 MYBOX", "문서뷰어", "PDF용 Adobe Acrobat Reader", "똑똑계산기", "vFlat", "모두 언어 번역하다 앱", "뤼튼", "한컴오피스 Viewer", "클로바노트", "인공지능 챗봇", "PDF Reader Pro", "문서뷰어, PDF 파일 읽기", "WiFi 비밀번호 지도 Instabridge", "daglo", "Google 문서", "네이버 클로바", "Google Calendar", "네이버 캘린더", "Notion", "Google 프레젠테이션", "타임스프레드", "메모장", "Send Anywhere", "Ask Ai", "삼성 모바일 프린트", "Microsoft Excel", "ChatGPT powered Chat", "Easy Notes"},//생산성
            {"당근", "Threads", "Instagram", "밴드", "네이버 카페", "X", "Facebook", "위버스", "Locket Widget", "네이버 블로그", "비고 라이브", "Pi Network", "다음 카페", "스푼", "겜톡톡 시즌3", "NGL", "싸이월드", "위피", "카카오스토리", "밤중년", "Diggus 디거스", "소모임", "다톡", "빠른톡S", "asked 에스크", "OMG", "디시인사이드", "1km 근처톡", "정오의데이트 소개팅", "포스타입 POSTYPE", "0모0모", "Reddit", "톡친구만들기", "밤친구", "CELEBe", "문토", "마음", "일상카페", "헬로우봇", "글램", "ifland", "동네친구 필요할 때 대화 수다 채팅하자", "심쿵", "Facebook Lite", "아카라이브"},//소셜
            {"쿠팡이츠", "배달의민족", "스타벅스", "캐치테이블", "땡겨요", "BBQ 치킨", "메가MGC커피 멤버십", "팥도감", "McDonald's", "교촌치킨", "테이블링", "버거킹", "해피오더", "컴포즈커피", "쿠캣", "배달특급", "패스오더", "bhc", "빽다방", "요기요", "컬리", "데일리샷", "대상웰라이프", "처갓집양념치킨", "도미노피자", "엽기떡볶이", "롯데잇츠", "KFC Korea", "더벤티", "파리바게뜨", "배달K", "월간푸드", "못난이마켓", "써브웨이", "CJ더마켓", "맘스터치", "이디야멤버스", "요리백과 만개의레시피", "Paul Bassett Crown Order", "자담치킨", "프레딧", "공차 멤버십", "맥도날드", "네네치킨", "커피빈"},//식음료
            {"Play 스토어","Play 뮤직","Play 무비/TV","YouTube","YT Music","Music","Spotify","멜론","지니뮤직","삼성 뮤직","라디오","DramaBox", "쿠팡플레이", "TVING", "Disney+", "WePlay", "치지직", "에버랜드", "7080 음악다방 추억의 트로트 노래모음", "티켓링크", "롯데시네마", "미스트플레이", "Wavve", "CGV", "악어 물린 손", "Youtube Kids", "카카오페이지", "팬더티비", "러비더비", "메가박스", "지니 TV", "전화 벨소리 설정", "ZEPETO", "인터파크 티켓", "삼성 TV 플러스", "Steam", "플레이오", "아프리카TV", "롯데월드 어드벤처", "왓챠", "ShortMax", "전화 벨소리 노래 바꾸기", "모바일 B tv", "TVING", "하쿠나", "신나는 연주음악", "이모티콘", "네이버 시리즈온", "7080 노래모음", "ZUICY(쥬씨)", "U+모바일tv", "PLAYTIME", "4shared", "이모티콘・멋진 생활","네이버 웹툰","NOW","시리즈온","시리즈","KBS KONG","SBS","SPOTV NOW","카카오웹툰","넷\uFEFF플\uFEFF릭\uFEFF스"},//엔터테인먼트
            {"Trip.com", "아고다", "코레일톡", "대한항공 My", "야놀자", "Grab", "여기어때", "SRT", "쏘카", "Airbnb", "지쿠", "아시아나항공", "스카이스캐너", "THE LOUNGE", "클룩", "진에어", "제주항공", "호텔스컴바인", "한달살기", "티웨이항공", "트리플", "스윙, Your Smarth WING", "플러스팟", "하나투어", "인터파크 투어", "마이리얼트립", "유니버설 스튜디오 재팬", "와그 WAUG", "두루누비", "호텔 예약은 Booking.com", "렌트카 카모아", "손전등", "빔| Beam", "에어부산", "케이케이데이 KKday", "Eastar Jet", "캠핏", "로밍도깨비 eSIM", "소노호텔앤리조트", "호텔스닷컴", "킥고잉", "대전시 타슈", "씽씽", "AustralianETA"},//여행
            {"카카오맵","지도","네이버 지도", "내비게이션", "티맵", "티머니GO", "카카오 T", "모바일티머니", "Uber Taxi", "카카오내비", "고속버스 티머니", "[공식]전국 시외버스 승차권 통합예매", "쏘카일레클", "전국 스마트 버스", "모바일이즐", "지하철 종결자", "따릉이", "카카오버스", "GPS, 지도, 음성 내비게이션, 운전 경로 및 목적지", "음성 GPS 내비게이션", "이즐충전소", "실시간 GPS지도, 교통, 경로 및 내비게이션", "카카오지하철", "티머니 갤럭시워치", "U+카카오내비", "내 위치", "투루카", "스마트무브", "GPS+ 지도, 내비게이션, 교통", "그린카", "GPS 지도 네비게이션", "Bolt", "주정차단속알림서비스 전국가입도우미", "위치추적어플", "카카오 T대리", "GPS 좌표 지도", "똑타", "오피넷", "Bikemap", "GNET", "나이스파크", "타다", "아이파킹", "Japan Transit Planner", "카카오 T 대리 기사용", "실시간 지구 지도 위성 보기", "GPS 위성 지도 항해 & 살다 교통", "GPS 내비게이션"},//지도
            {"Temu", "AliExpress", "쿠팡", "SHEIN", "무신사", "Nike", "올리브영", "티몬", "KREAM", "포스티", "번개장터", "지그재그", "29CM", "스탬플리", "오늘의집", "신세계쇼핑", "에리블리", "4910", "인터파크 쇼핑", "11번가", "중고나라", "롯데ON", "떠리몰", "GS SHOP", "G마켓", "SK스토아", "W컨셉", "홈플러스", "폴센트", "삼성닷컴", "SSFSHOP", "룩핀", "현대홈쇼핑", "롯데백화점", "KT알파 쇼핑", "그립 Grip", "NS홈쇼핑", "CJ온스타일", "SSG.COM", "이랜드몰", "올웨이즈", "샵백", "ABC-MART", "위메프", "롯데홈쇼핑"},//쇼핑
            {"Zoom","인터넷","Meet","연락처", "전화","Gmail","Chrome","메시지","카카오톡", "라인 LINE", "Snapchat", "Telegram", "Discord", "WhatsApp", "아이쉐어링", "안전신문고", "T전화", "GAYO", "WeChat", "Messenger", "다음", "중년천국", "넥슨플레이", "보이는 ARS", "Whale", "후후", "Brave 개인 웹 브라우저", "Microsoft Edge", "T전화", "Samsung Internet", "Personal Stickers for WA", "대국민 국군 소통 서비스 더캠프", "Pi Browser", "네이버 메일", "말톡", "Personal Stickers", "카카오톡 채널 관리자", "Google 메세지", "주소록", "다음 네일", "Zalo", "구글메일", "키즈노트", "돌싱의밤", "랜덤톡", "메신저", "스마트 공지시스템 e알리미", "와이파이 도시락", "U+스팸차단", "중년소개팅", "이메일", "앙팅 즐팅 채팅 톡톡 랜덤채팅", "등산친구","에브리타임"},//커뮤니케이션
            {},//기타및게임
    };
    @SuppressLint("QueryPermissionsNeeded")
    public static void init(Context c){
        context=c;
        pm = context.getPackageManager();
        app_info_list = new ArrayList<AppInfo>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppList = pm.queryIntentActivities(mainIntent, 0);

        for(ResolveInfo resolveInfo : pkgAppList){
            try {
                String pkgName = resolveInfo.activityInfo.packageName;
                String app_name = resolveInfo.activityInfo.loadLabel(pm).toString();

                Drawable icon = pm.getApplicationIcon(pkgName);

                boolean same = false;
                for(int i=0;i<app_info_list.size();i++){
                    if(pkgName.equals(app_info_list.get(i).getAppPackage()))
                        same=true;
                }
                if(!same){
                    app_info_list.add(new AppInfo(app_name, pkgName, icon));
                }
                //for(int i=0;i<app_info_list.size();i++){
                //    Log.d("Info", "package name :" + app_info_list.get(i).getAppPackage()+"//app_name: "+app_info_list.get(i).getAppName());
                //}

            }catch(Exception e){
                Log.d("bug","버그남");
            }
        }
    }

    static private Bitmap drawableToBitmap(Drawable d){
        if(d instanceof BitmapDrawable)
            return ((BitmapDrawable)d).getBitmap();

        int width=d.getIntrinsicWidth();
        int height=d.getIntrinsicHeight();
        if(width<=0) width=1;
        if(height<=0)height=1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        d.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        d.draw(canvas);

        return bitmap;
    }
    public static ArrayList<AppInfo>[] getColorOrderApps(){
        ArrayList<AppInfo>[] color_order_apps=new ArrayList[getCountOfColor()];
        //빨주노초파보검
        for(int i=0;i<getCountOfColor();i++){
            color_order_apps[i] = new ArrayList<AppInfo>();
        }
        for(int i=0;i< app_info_list.size();i++){
            Drawable icon = app_info_list.get(i).getAppIcon();
            Bitmap bm = drawableToBitmap(icon);
            int dvdHeight=bm.getHeight()/(DIVIDED+1);
            int dvdWidth=bm.getWidth()/(DIVIDED+1);

            PixelColor[] pc = new PixelColor[DIVIDED*DIVIDED];
            for(int j=1;j<=DIVIDED;j++){
                for(int k=1;k<=DIVIDED;k++){
                    int pixel=bm.getPixel(k*dvdWidth, j*dvdHeight);
                    int redVal= Color.red(pixel);
                    int greenVal=Color.green(pixel);
                    int blueVal=Color.blue(pixel);

                    pc[(j-1)*DIVIDED+k-1]=new PixelColor(redVal,greenVal,blueVal);
                }
            }

            ArrayList<Integer> aaa = new ArrayList<Integer>();
            for(int j=0;j<pc.length;j++){
                aaa.add(pc[j].getMostCloseColor());
            }
            color_order_apps[ListFuncZip.getMostCommonIndex(aaa)].add(app_info_list.get(i));
        }
        return color_order_apps;
    }

    public static ArrayList<AppInfo>[] getTypeOrderApps(){
        ArrayList<AppInfo>[] type_order_apps = new ArrayList[getCountOfType()];
        for(int i=0;i<getCountOfType();i++){
            type_order_apps[i] = new ArrayList<AppInfo>();
        }
        for(int i=0;i< app_info_list.size();i++){
            for(int j=0;j<category_dict.length;j++){
                if(Arrays.asList(category_dict[j]).contains(app_info_list.get(i).getAppName())){
                    type_order_apps[j].add(app_info_list.get(i));
                    break;
                }
                if(j== category_dict.length-1) type_order_apps[j].add(app_info_list.get(i));
            }
        }
        for(int i=0;i<getCountOfType();i++){
            for(int j=0;j<type_order_apps[i].size();j++){
                Log.d("ddd", "category:" + i + " ///app_name:"+type_order_apps[i].get(j).getAppName());
            }
        }
        return type_order_apps;
    }

    public static ArrayList<AppInfo> getAllItemList(){
        return app_info_list;
    }

    public static String[] getTypeTags(){
        return type_tags;
    }
    public static String[] getColorTags(){
        return color_tags;
    }
    public static int getCountOfType(){
        return type_tags.length;
    }
    public static int getCountOfColor(){
        return color_tags.length;
    }
}
