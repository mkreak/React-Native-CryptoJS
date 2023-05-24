@GetMapping("/api/getPrivateKey")
    @ResponseBody
    public ResponseEntity<?> getPrivateKey(){
        HashMap<String, String> result = new HashMap<>();
        try {
            HashMap<String, String> rate = testService.getEncryptandSecret( );
            if (rate != null) {
//                result.put("message", rate);
                return new ResponseEntity<>(rate, HttpStatus.ACCEPTED);
            } else {
                result.put("message", "something Went Wrong");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("err", e.getLocalizedMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
