# REDIRECTATTRDEMO

- Spring RedirectAttribute로 FlashMap을 이용하여 Redirection URL로 이동후 API 호출시 InputFlashMap을 획득하는 샘플
- 기본 RedirectView는 OutputFlashMap을 Redirect URL을 FlashMap targetURL로 처리하므로 Redirect후 호출할 API를 TargetURL로 지정불가
- RedirectView를 상속받아 최소한의 코드로 대체구현 처리