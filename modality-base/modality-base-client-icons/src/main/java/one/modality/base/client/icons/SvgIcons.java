package one.modality.base.client.icons;

import dev.webfx.extras.panes.MonoPane;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Bruno Salmon
 */
public final class SvgIcons {

    private static final String TRASH_PATH = "M 6.3952 2.7317 H 9.4429 C 9.4429 2.3177 9.2823 1.9207 8.9965 1.6279 C 8.7107 1.3352 8.3232 1.1707 7.919 1.1707 C 7.5149 1.1707 7.1273 1.3352 6.8416 1.6279 C 6.5558 1.9207 6.3952 2.3177 6.3952 2.7317 Z M 5.2524 2.7317 C 5.2524 2.373 5.3214 2.0178 5.4554 1.6863 C 5.5894 1.3549 5.7858 1.0538 6.0334 0.8001 C 6.2811 0.5464 6.575 0.3452 6.8986 0.2079 C 7.2221 0.0707 7.5689 0 7.919 0 C 8.2692 0 8.616 0.0707 8.9395 0.2079 C 9.263 0.3452 9.5571 0.5464 9.8047 0.8001 C 10.0523 1.0538 10.2487 1.3549 10.3827 1.6863 C 10.5168 2.0178 10.5857 2.373 10.5857 2.7317 H 14.9667 C 15.1182 2.7317 15.2636 2.7934 15.3707 2.9032 C 15.4779 3.0129 15.5381 3.1618 15.5381 3.3171 C 15.5381 3.4723 15.4779 3.6212 15.3707 3.731 C 15.2636 3.8408 15.1182 3.9024 14.9667 3.9024 H 13.961 L 13.0695 13.3549 C 13.0011 14.0792 12.6718 14.7514 12.1459 15.2405 C 11.6198 15.7295 10.9349 16.0003 10.2246 16 H 5.6135 C 4.9033 16.0001 4.2186 15.7292 3.6927 15.2402 C 3.1669 14.7512 2.8377 14.0791 2.7693 13.3549 L 1.8771 3.9024 H 0.8714 C 0.7199 3.9024 0.5745 3.8408 0.4674 3.731 C 0.3602 3.6212 0.3 3.4723 0.3 3.3171 C 0.3 3.1618 0.3602 3.0129 0.4674 2.9032 C 0.5745 2.7934 0.7199 2.7317 0.8714 2.7317 H 5.2524 Z M 6.7762 6.439 C 6.7762 6.2838 6.716 6.1349 6.6088 6.0251 C 6.5017 5.9153 6.3563 5.8537 6.2048 5.8537 C 6.0532 5.8537 5.9079 5.9153 5.8007 6.0251 C 5.6935 6.1349 5.6333 6.2838 5.6333 6.439 V 12.2926 C 5.6333 12.4479 5.6935 12.5968 5.8007 12.7066 C 5.9079 12.8164 6.0532 12.878 6.2048 12.878 C 6.3563 12.878 6.5017 12.8164 6.6088 12.7066 C 6.716 12.5968 6.7762 12.4479 6.7762 12.2926 V 6.439 Z M 9.6333 5.8537 C 9.4818 5.8537 9.3364 5.9153 9.2293 6.0251 C 9.1221 6.1349 9.0619 6.2838 9.0619 6.439 V 12.2926 C 9.0619 12.4479 9.1221 12.5968 9.2293 12.7066 C 9.3364 12.8164 9.4818 12.878 9.6333 12.878 C 9.7849 12.878 9.9302 12.8164 10.0374 12.7066 C 10.1446 12.5968 10.2048 12.4479 10.2048 12.2926 V 6.439 C 10.2048 6.2838 10.1446 6.1349 10.0374 6.0251 C 9.9302 5.9153 9.7849 5.8537 9.6333 5.8537 Z";
    private static final String PLUS_PATH = "M9.8975 18.9698C15.0129 18.9698 19.1597 14.823 19.1597 9.70756C19.1597 4.59216 15.0129 0.445312 9.8975 0.445312C4.7821 0.445312 0.635254 4.59216 0.635254 9.70756C0.635254 14.823 4.7821 18.9698 9.8975 18.9698ZM8.57465 5.29908H11.221V8.3878H14.308V11.0342H11.221V14.1203H8.57465V11.0342H5.48678V8.3878H8.57465V5.29908Z";
    private static final String MINUS_PATH = "M 8.75 17.5 C 6.4294 17.5 4.2038 16.5781 2.5628 14.9372 C 0.9219 13.2962 0 11.0706 0 8.75 C 0 6.4294 0.9219 4.2038 2.5628 2.5628 C 4.2038 0.9219 6.4294 0 8.75 0 C 11.0706 0 13.2962 0.9219 14.9372 2.5628 C 16.5781 4.2038 17.5 6.4294 17.5 8.75 C 17.5 11.0706 16.5781 13.2962 14.9372 14.9372 C 13.2962 16.5781 11.0706 17.5 8.75 17.5 Z M 8.75 15.75 C 10.6065 15.75 12.387 15.0125 13.6998 13.6998 C 15.0125 12.387 15.75 10.6065 15.75 8.75 C 15.75 6.8935 15.0125 5.113 13.6998 3.8003 C 12.387 2.4875 10.6065 1.75 8.75 1.75 C 6.8935 1.75 5.113 2.4875 3.8003 3.8003 C 2.4875 5.113 1.75 6.8935 1.75 8.75 C 1.75 10.6065 2.4875 12.387 3.8003 13.6998 C 5.113 15.0125 6.8935 15.75 8.75 15.75 Z M 13.125 7.875 V 9.625 H 4.375 V 7.875 H 13.125 Z";
    private static final String BACK_ARROW_PATH = "M 0 17.5 C 0 27.1645 7.8354 35 17.5 35 C 27.1645 35 35 27.1645 35 17.5 C 35 7.8354 27.1645 0 17.5 0 C 7.8354 0 0 7.8354 0 17.5 Z M 21.2019 24.9038 L 10.2569 17.5 L 21.2019 11.5265 V 24.9038 Z";
    private static final String TOP_ARROW_PATH = "M14 28C6.25882 28 0 21.7412 0 14C0 6.25882 6.25882 0 14 0C21.7412 0 28 6.25882 28 14C28 21.7412 21.7412 28 14 28ZM14 1.64706C7.16471 1.64706 1.64706 7.16471 1.64706 14C1.64706 20.8353 7.16471 26.3529 14 26.3529C20.8353 26.3529 26.3529 20.8353 26.3529 14C26.3529 7.16471 20.8353 1.64706 14 1.64706Z M20.8352 15.4L14 8.56467L7.16466 15.4L6.01172 14.247L14 6.25879L21.9882 14.247L20.8352 15.4Z M13.1758 7.41162H14.8228L14.8228 21.4116H13.1758L13.1758 7.41162Z";
    private static final String BOTTOM_ARROW_PATH = "M14 28C6.259 28 0 21.741 0 14C0 6.259 6.259 0 14 0C21.741 0 28 6.259 28 14C28 21.741 21.741 28 14 28ZM14 1.647C7.164 1.647 1.647 7.165 1.647 14C1.647 20.835 7.164 26.353 14 26.353C20.835 26.353 26.353 20.835 26.353 14C26.353 7.165 20.835 1.647 14 1.647Z M20.8357 12.271L14.0007 19.106L7.16473 12.271L6.01172 13.424L14.0007 21.412L21.9887 13.424L20.8357 12.271Z M13.1758 20.2588H14.8228V6.25879H13.1758V20.2588Z";
    private static final String PINPOINT_PATH = "M5.5 7.68117C5.87813 7.68117 6.20194 7.54035 6.47144 7.25869C6.74048 6.97751 6.875 6.63934 6.875 6.24416C6.875 5.84898 6.74048 5.51056 6.47144 5.22891C6.20194 4.94773 5.87813 4.80714 5.5 4.80714C5.12187 4.80714 4.79829 4.94773 4.52925 5.22891C4.25975 5.51056 4.125 5.84898 4.125 6.24416C4.125 6.63934 4.25975 6.97751 4.52925 7.25869C4.79829 7.54035 5.12187 7.68117 5.5 7.68117ZM5.5 14.5968C5.40833 14.5968 5.31667 14.5788 5.225 14.5429C5.13333 14.507 5.05312 14.4591 4.98438 14.3992C3.31146 12.8544 2.0625 11.4205 1.2375 10.0975C0.4125 8.77402 0 7.53747 0 6.38786C0 4.59159 0.552979 3.16056 1.65894 2.09477C2.76444 1.02899 4.04479 0.496094 5.5 0.496094C6.95521 0.496094 8.23556 1.02899 9.34106 2.09477C10.447 3.16056 11 4.59159 11 6.38786C11 7.53747 10.5875 8.77402 9.7625 10.0975C8.9375 11.4205 7.68854 12.8544 6.01562 14.3992C5.94688 14.4591 5.86667 14.507 5.775 14.5429C5.68333 14.5788 5.59167 14.5968 5.5 14.5968Z";
    private static final String SOUND_INACTIVE_PATH = "M4.69516 7.17147L0.157163 1.4879C-0.0906157 1.17757 -0.0399097 0.725135 0.270418 0.477357C0.580746 0.229578 1.03318 0.280285 1.28096 0.590612L19.0375 22.8296C19.2852 23.1399 19.2345 23.5923 18.9242 23.8401C18.6139 24.0879 18.1614 24.0372 17.9137 23.7269L14.9173 19.974H6.65421C6.11547 19.974 5.65444 19.7789 5.27112 19.3886C4.8878 18.9983 4.69581 18.5286 4.69516 17.9794V7.17147ZM13.3246 17.9794H6.65421V9.62506L9.5931 13.3058C9.26638 13.7065 9.10303 14.1839 9.10303 14.738C9.10303 15.3697 9.31526 15.9016 9.73972 16.3338C10.1642 16.766 10.6866 16.982 11.307 16.982C11.6862 16.982 12.0288 16.9013 12.3349 16.7398L13.3246 17.9794ZM12.5314 11.7216L13.5109 12.9484V10.998H16.4495V9.00336H12.5314V11.7216ZM18.4085 17.9794H17.5278L19.0402 19.8735C19.3177 19.7788 19.5685 19.6172 19.7926 19.3886C20.1759 18.9977 20.3676 18.5279 20.3676 17.9794V6.01135L14.4904 0.0273438H6.65421C6.11612 0.0280086 5.65509 0.223486 5.27112 0.613777C4.9134 0.977381 4.72231 1.40966 4.69785 1.9106L6.65421 4.36083V2.02201H13.5109V7.00869H18.4085V17.9794Z";
    private static final String SOUND_PATH = "M7.47457 17.5108C8.0903 17.5108 8.60882 17.2891 9.03011 16.8456C9.45141 16.4022 9.66205 15.8564 9.66205 15.2082V11.3705H12.5787V9.32375H8.68984V13.2894C8.5116 13.1529 8.32137 13.0547 8.11915 12.9946C7.91692 12.9346 7.70206 12.9049 7.47457 12.9056C6.85883 12.9056 6.34031 13.1273 5.91902 13.5708C5.49773 14.0143 5.28708 14.5601 5.28708 15.2082C5.28708 15.8564 5.49773 16.4022 5.91902 16.8456C6.34031 17.2891 6.85883 17.5108 7.47457 17.5108ZM2.85654 20.581C2.32182 20.581 1.86423 20.3807 1.48377 19.9803C1.10331 19.5798 0.912758 19.0978 0.912109 18.5342V2.16005C0.912109 1.59719 1.10266 1.11552 1.48377 0.715032C1.86488 0.314547 2.32247 0.113964 2.85654 0.113281H10.6343L16.4676 6.25359V18.5342C16.4676 19.0971 16.2773 19.5791 15.8969 19.9803C15.5164 20.3814 15.0585 20.5817 14.5231 20.581H2.85654ZM9.66205 7.27698V2.16005H2.85654V18.5342H14.5231V7.27698H9.66205Z";
    private static final String VIDEO_INACTIVE_PATH = "M1.47169 0.268979C1.15181 -0.073225 0.615089 -0.0913252 0.272885 0.228551C-0.0693189 0.548427 -0.0874188 1.08515 0.232457 1.42735L4.02634 5.48605C3.77761 5.82348 3.65324 6.21125 3.65324 6.64936V18.6494C3.65391 19.2 3.84991 19.671 4.24124 20.0624C4.63258 20.4537 5.10324 20.6494 5.65324 20.6494H9.65324V22.6494H17.6532V20.6494H18.2003L22.2917 25.0264C22.6116 25.3686 23.1483 25.3867 23.4906 25.0668C23.8328 24.747 23.8509 24.2102 23.531 23.868L1.47169 0.268979ZM13.5115 15.6333L11.1532 13.1104V17.1494L13.5115 15.6333ZM18.1532 12.6494L16.3497 13.8088L22.5506 20.4425C22.7356 20.3481 22.9075 20.2214 23.0662 20.0624C23.4576 19.6704 23.6532 19.1994 23.6532 18.6494V6.64936C23.6539 6.10003 23.4582 5.62936 23.0662 5.23736C22.6742 4.84536 22.2032 4.64936 21.6532 4.64936H7.78795L11.1532 8.24956V8.14936L18.1532 12.6494Z";
    private static final String VIDEO_PATH = "M7.50391 12.5L14.5039 8L7.50391 3.5V12.5ZM6.00391 18V16H2.00391C1.45391 16 0.98324 15.8043 0.591906 15.413C0.200573 15.0217 0.00457292 14.5507 0.00390625 14V2C0.00390625 1.45 0.199906 0.979333 0.591906 0.588C0.983906 0.196667 1.45457 0.000666667 2.00391 0H18.0039C18.5539 0 19.0249 0.196 19.4169 0.588C19.8089 0.98 20.0046 1.45067 20.0039 2V14C20.0039 14.55 19.8082 15.021 19.4169 15.413C19.0256 15.805 18.5546 16.0007 18.0039 16H14.0039V18H6.00391Z";
    private static final String VIDEO_PLAY_PATH = "M 12.4313 0.1625 C 10.0208 0.1625 7.6645 0.8773 5.6602 2.2165 C 3.656 3.5556 2.0939 5.4591 1.1715 7.6861 C 0.249 9.913 0.0077 12.3635 0.4779 14.7277 C 0.9482 17.0918 2.1089 19.2634 3.8134 20.9679 C 5.5179 22.6723 7.6894 23.8331 10.0536 24.3033 C 12.4177 24.7736 14.8682 24.5322 17.0952 23.6098 C 19.3222 22.6873 21.2256 21.1252 22.5648 19.121 C 23.904 17.1168 24.6188 14.7605 24.6188 12.35 C 24.6188 9.1177 23.3347 6.0177 21.0491 3.7321 C 18.7635 1.4465 15.6636 0.1625 12.4313 0.1625 Z M 18.9141 13.1291 L 8.4677 18.3523 C 8.335 18.4187 8.1874 18.45 8.0392 18.4433 C 7.8909 18.4365 7.7469 18.392 7.6206 18.314 C 7.4944 18.2359 7.3903 18.1269 7.3181 17.9972 C 7.2458 17.8676 7.208 17.7216 7.208 17.5732 V 7.1268 C 7.2081 6.9785 7.2461 6.8326 7.3184 6.7031 C 7.3907 6.5735 7.4948 6.4646 7.621 6.3867 C 7.7472 6.3087 7.8912 6.2643 8.0394 6.2576 C 8.1876 6.2509 8.335 6.2822 8.4677 6.3485 L 18.9141 11.5718 C 19.0586 11.6441 19.18 11.7553 19.2648 11.8927 C 19.3497 12.0301 19.3946 12.1885 19.3946 12.35 C 19.3946 12.5115 19.3497 12.6699 19.2648 12.8073 C 19.18 12.9447 19.0586 13.0559 18.9141 13.1282";
    private static final String CHECKMARK_PATH = "M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm0 18a8 8 0 1 1 8-8 8 8 0 0 1-8 8z M14.7 8.39l-3.78 5-1.63-2.11a1 1 0 0 0-1.58 1.23l2.43 3.11a1 1 0 0 0 .79.38 1 1 0 0 0 .79-.39l4.57-6a1 1 0 1 0-1.6-1.22z";
    private static final String DHARMA_WHEEL_PATH = "M54.6002 10.561C30.3226 10.561 10.5752 30.3084 10.5752 54.6C10.5752 78.8915 30.3226 98.6249 54.6002 98.6249C78.8777 98.6249 98.6392 78.8775 98.6392 54.6C98.6392 30.3224 78.8777 10.561 54.6002 10.561ZM80.5186 49.621C80.5046 48.7936 80.3924 48.2045 80.3924 48.2045C74.3336 54.5859 69.2845 51.8791 67.8539 50.8833C67.4893 49.607 66.9563 48.4008 66.2691 47.3069C66.6758 45.7781 68.6674 40.6029 77.1947 40.5748C77.1947 40.5748 76.7739 39.9577 76.0867 39.2705C77.0404 32.4683 81.9913 30.8413 83.5481 30.5047C88.2185 36.1148 91.304 43.0853 92.0894 50.743C91.0515 51.4724 86.2549 54.2914 80.5046 49.6351L80.5186 49.621ZM81.5144 54.628C80.6028 54.9927 80.1119 55.5817 79.8595 56.2269C79.5088 55.5537 78.9899 54.9786 78.1765 54.6841C78.9759 54.3335 79.5088 53.7304 79.8595 53.0432C80.1119 53.6883 80.6028 54.2774 81.5004 54.642L81.5144 54.628ZM73.9689 73.562C73.0573 73.1833 72.2999 73.2394 71.6688 73.5199C71.9072 72.7906 71.9353 72.0192 71.5846 71.2338C72.3981 71.5564 73.1975 71.5003 73.9409 71.2619C73.6603 71.907 73.6042 72.6644 73.9689 73.548V73.562ZM78.7094 83.5619C73.0993 88.2323 66.1288 91.3178 58.4711 92.1032C57.7418 91.0654 54.9227 86.2688 59.5791 80.5185C60.4066 80.5044 60.9956 80.3922 60.9956 80.3922C54.6142 74.3334 57.321 69.2843 58.3168 67.8537C59.5931 67.4891 60.7993 66.9561 61.8932 66.2689C63.422 66.6756 68.5973 68.6672 68.6253 77.1945C68.6253 77.1945 69.2424 76.7737 69.9297 76.0865C76.7319 77.0402 78.3588 81.9911 78.6954 83.5479L78.7094 83.5619ZM35.4979 71.4442C36.2272 71.6826 36.9986 71.7107 37.784 71.36C37.4754 72.1735 37.5175 72.9729 37.756 73.7163C37.1108 73.4358 36.3534 73.3797 35.4698 73.7443C35.8626 72.8327 35.7924 72.0753 35.5119 71.4582L35.4979 71.4442ZM39.7054 76.5914C40.2945 77.1664 40.7854 77.503 40.7854 77.503C40.547 68.1903 46.69 66.886 48.0083 66.7037C49.0181 67.2507 50.0981 67.6854 51.2341 67.9659C52.0897 69.5087 54.0111 74.4596 48.1907 80.3081C48.1907 80.3081 48.92 80.4483 49.9017 80.4624C54.2495 86.2127 51.4305 91.0513 50.7152 92.1032C43.1977 91.3318 36.3254 88.3445 30.7714 83.8003C31.094 82.2576 32.6929 77.3207 39.7054 76.5914ZM35.2174 35.638C36.129 36.0166 36.8864 35.9605 37.5175 35.68C37.2791 36.4093 37.251 37.1807 37.6017 37.9661C36.7882 37.6435 35.9888 37.6996 35.2454 37.9381C35.5259 37.2929 35.582 36.5356 35.2174 35.652V35.638ZM30.4909 25.638C36.101 20.9676 43.0715 17.8821 50.7292 17.0967C51.4585 18.1346 54.2776 22.9312 49.6212 28.6815C48.7937 28.6955 48.2047 28.8077 48.2047 28.8077C54.5861 34.8666 51.8793 39.9156 50.8835 41.3462C49.6072 41.7108 48.401 42.2438 47.3071 42.931C45.7783 42.5243 40.6031 40.5327 40.575 32.0054C40.575 32.0054 39.9579 32.4262 39.2707 33.1134C32.4685 32.1597 30.8415 27.2088 30.5049 25.652L30.4909 25.638ZM73.5201 37.5173C72.7908 37.2789 72.0194 37.2508 71.234 37.6015C71.5426 36.788 71.5005 35.9886 71.262 35.2452C71.9072 35.5258 72.6646 35.5819 73.5481 35.2172C73.1554 36.1288 73.2256 36.8862 73.5061 37.5033L73.5201 37.5173ZM69.3125 32.3841C68.7235 31.8091 68.2326 31.4725 68.2326 31.4725C68.4851 41.5706 61.2481 42.2578 60.7712 42.2859C59.8736 41.8371 58.9339 41.4864 57.9522 41.234C57.0966 39.6912 55.1752 34.7403 60.9956 28.8919C60.9956 28.8919 60.2663 28.7516 59.2846 28.7376C54.9368 22.9873 57.7558 18.1486 58.4711 17.0967C65.9044 17.8541 72.7066 20.7993 78.2325 25.2593C77.8679 26.8863 76.2129 31.6688 69.3266 32.3841H69.3125ZM54.516 78.1903C54.8666 78.9897 55.4697 79.5227 56.157 79.8733C55.5118 80.1258 54.9227 80.6166 54.5581 81.5142C54.1934 80.6026 53.6044 80.1117 52.9592 79.8733C53.6324 79.5227 54.2075 79.0037 54.502 78.1903H54.516ZM54.6002 62.0473C50.4908 62.0473 47.1528 58.7093 47.1528 54.6C47.1528 50.4906 50.4908 47.1526 54.6002 47.1526C58.7095 47.1526 62.0475 50.4906 62.0475 54.6C62.0475 58.7093 58.7095 62.0473 54.6002 62.0473ZM54.6843 31.0097C54.3337 30.2102 53.7306 29.6773 53.0434 29.3266C53.6885 29.0742 54.2776 28.5833 54.6422 27.6857C55.0069 28.5973 55.5959 29.0882 56.2411 29.3266C55.5679 29.6773 54.9929 30.1962 54.6983 31.0097H54.6843ZM25.2455 30.9676C26.8724 31.3322 31.655 32.9872 32.3703 39.8736C31.7953 40.4626 31.4587 40.9535 31.4587 40.9535C41.5568 40.701 42.244 47.938 42.272 48.4149C41.8092 49.3265 41.4586 50.2802 41.2061 51.29C39.9719 52.0334 34.8107 54.5158 28.6396 48.3728C28.6396 48.3728 28.4993 49.1021 28.4853 50.0839C23.0436 54.1932 18.4293 51.8931 17.0548 51.0236C17.7701 43.464 20.7154 36.5636 25.2315 30.9536L25.2455 30.9676ZM30.7714 54.6981C29.972 55.0488 29.439 55.6519 29.0884 56.3391C28.8359 55.6939 28.3451 55.1049 27.4475 54.7402C28.3591 54.3756 28.85 53.7865 29.1024 53.1413C29.4531 53.8146 29.972 54.3896 30.7854 54.6841L30.7714 54.6981ZM17.0969 58.4849C18.4994 57.6014 23.0576 55.3713 28.4432 59.7472C28.4573 60.5747 28.5695 61.1637 28.5695 61.1637C35.4278 53.9408 40.9817 58.3447 41.4726 58.7654C41.7952 59.7753 42.23 60.729 42.7629 61.6266C42.3843 63.0992 40.4207 68.3727 31.8373 68.4007C31.8373 68.4007 32.2581 69.0178 32.9453 69.7051C31.9916 76.5353 26.9987 78.1342 25.4699 78.4708C20.9117 72.9028 17.8963 66.0305 17.1249 58.4849H17.0969ZM83.9408 78.2324C82.3139 77.8677 77.5313 76.2127 76.816 69.3264C77.391 68.7373 77.7276 68.2464 77.7276 68.2464C67.6436 68.4989 66.9423 61.2619 66.9142 60.7851C67.3631 59.8875 67.7137 58.9478 67.9661 57.966C69.5089 57.1105 74.4598 55.189 80.3083 61.0095C80.3083 61.0095 80.4485 60.2802 80.4625 59.2984C86.2129 54.9506 91.0515 57.7697 92.1034 58.4849C91.3461 65.9183 88.4008 72.7205 83.9408 78.2464V78.2324Z " +
        "M54.6284 8.92C55.3156 7.12478 56.6901 5.49786 59.2427 4.46C56.7602 3.43616 55.3857 1.80925 54.6705 0C53.6747 1.93547 52.188 3.60447 49.958 4.60025C52.2161 5.44176 53.6887 7.04063 54.6424 8.93402L54.6284 8.92Z " +
        "M86.9139 22.3281C88.667 21.5426 90.7988 21.3603 93.3374 22.4403C92.2995 19.9718 92.4959 17.84 93.2672 16.0448C91.2056 16.718 88.9615 16.8442 86.6895 15.9606C87.6852 18.1486 87.6011 20.3225 86.9419 22.3421L86.9139 22.3281Z " +
        "M104.6 49.9576V49.9436C103.758 52.2016 102.16 53.6743 100.266 54.628C102.061 55.3152 103.688 56.6897 104.726 59.2423C105.75 56.7598 107.377 55.3853 109.186 54.67C107.251 53.6743 105.582 52.1876 104.586 49.9576H104.6Z " +
        "M86.8715 86.9139C87.6569 88.667 87.8392 90.7989 86.7593 93.3374C89.2277 92.2995 91.3595 92.4959 93.1547 93.2673C92.4815 91.2056 92.3553 88.9616 93.2389 86.6895C91.051 87.6853 88.8771 87.6011 86.8575 86.9419L86.8715 86.9139Z " +
        "M54.5576 100.28C53.8704 102.075 52.4959 103.702 49.9434 104.74C52.4258 105.764 53.8003 107.391 54.5156 109.2C55.5113 107.264 56.998 105.595 59.228 104.6C56.97 103.758 55.4973 102.159 54.5436 100.266L54.5576 100.28Z " +
        "M22.2717 86.8718C20.5185 87.6572 18.3867 87.8395 15.8481 86.7596C16.886 89.228 16.6897 91.3598 15.9183 93.1551C17.98 92.4818 20.224 92.3556 22.4961 93.2392C21.5003 91.0513 21.5844 88.8774 22.2436 86.8578L22.2717 86.8718Z " +
        "M4.46 49.9436C3.43616 52.426 1.80925 53.8005 0 54.5158C1.93547 55.5116 3.60447 56.9982 4.60025 59.2282V59.2423C5.44176 56.9842 7.04063 55.5116 8.93402 54.5579C7.1388 53.8706 5.51189 52.4962 4.47403 49.9436H4.46Z " +
        "M22.3279 22.2719C21.5425 20.5188 21.3601 18.387 22.4401 15.8484C19.9716 16.8863 17.8398 16.6899 16.0446 15.9185C16.7178 17.9802 16.844 20.2243 15.9604 22.4963C18.1484 21.5006 20.3223 21.5847 22.3419 22.2439L22.3279 22.2719Z";
    public static SVGPath createSVGPath(String path) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(path);
        return svgPath;
    }

    public static SVGPath createStrokeSVGPath(String path, Paint stroke, double strokeWidth) {
        return createSVGPath(path, null, stroke, strokeWidth);
    }

    public static SVGPath setSVGPathFill(SVGPath svgPath, Paint fill) {
        svgPath.setFill(fill);
        return svgPath;
    }

    public static SVGPath setSVGPathStroke(SVGPath svgPath, Paint stroke, double strokeWidth) {
        svgPath.setStroke(stroke);
        svgPath.setStrokeWidth(strokeWidth);
        return svgPath;
    }

    public static SVGPath createSVGPath(String path, Paint fill, Paint stroke, double strokeWidth) {
        return setSVGPathFillStroke(createSVGPath(path), fill, stroke, strokeWidth);
    }

    public static SVGPath setSVGPathFillStroke(SVGPath svgPath, Paint fill, Paint stroke, double strokeWidth) {
        svgPath.setFill(fill);
        return setSVGPathStroke(svgPath, stroke, strokeWidth);
    }

    public static MonoPane createButtonPane(Node buttonContent) {
        return new MonoPane(buttonContent);
    }

    public static MonoPane createButtonPane(Node buttonContent, Runnable onClicked) {
        return armButton(createButtonPane(buttonContent), onClicked);
    }

    public static <N extends Node> N armButton(N buttonNode, Runnable onClicked) {
        buttonNode.setCursor(onClicked == null ? Cursor.DEFAULT : Cursor.HAND);
        buttonNode.setOnMouseClicked(onClicked == null ? null : e -> {
            onClicked.run();
            e.consume();
        });
        return buttonNode;
    }

    public static MonoPane createToggleButtonPane(Node trueContent, Node falseContent, Supplier<Boolean> stateGetter, Consumer<Boolean> stateSetter) {
        MonoPane monoPane = new MonoPane(stateGetter.get() ? trueContent : falseContent);
        return armButton(monoPane, () -> {
            boolean oldState = stateGetter.get();
            boolean newState = !oldState;
            stateSetter.accept(newState);
            newState = stateGetter.get();
            monoPane.setContent(newState ? trueContent : falseContent);
        });
    }

    public static MonoPane createToggleButtonPane(Node trueContent, Node falseContent, boolean initialState, Consumer<Boolean> onClicked) {
        boolean[] state = { initialState };
        return createToggleButtonPane(trueContent, falseContent, () -> state[0], newState -> {
            state[0] = newState;
            onClicked.accept(newState);
        });
    }

    public static SVGPath createTrashSVGPath() {
        return createSVGPath(TRASH_PATH);
    }

    public static SVGPath createPinpointSVGPath() {
        return createSVGPath(PINPOINT_PATH);
    }

    public static SVGPath createPlusPath() {
        return createSVGPath(PLUS_PATH);
    }

    public static SVGPath createMinusPath() {
        return createSVGPath(MINUS_PATH);
    }

    public static SVGPath createTopArrowPath() {
        return createSVGPath(TOP_ARROW_PATH);
    }

    public static SVGPath createBottomArrowPath() {
        return createSVGPath(BOTTOM_ARROW_PATH);
    }

    public static SVGPath createSoundIconInactivePath() {
        return createSVGPath(SOUND_INACTIVE_PATH);
    }

    public static SVGPath createSoundIconPath() {
        return createSVGPath(SOUND_PATH);
    }

    public static SVGPath createVideoIconInactivePath() {
        return createSVGPath(VIDEO_INACTIVE_PATH);
    }

    public static SVGPath createVideoIconPath() {
        return createSVGPath(VIDEO_PATH);
    }

    public static SVGPath createBackArrow() {
        return createSVGPath(BACK_ARROW_PATH);
    }

    public static SVGPath createVideoPlaySVGPath() {
        return createSVGPath(VIDEO_PLAY_PATH);
    }

    public static SVGPath createCheckMarkSVGPath() {
        return createSVGPath(CHECKMARK_PATH);
    }

    public static SVGPath createDharmaWheelSVGPath() {
        return createSVGPath(DHARMA_WHEEL_PATH);
    }
}


