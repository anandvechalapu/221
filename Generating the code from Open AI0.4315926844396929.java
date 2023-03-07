
Controller:
@RestController
public class CodeGenerationController {
    
    @Autowired
    private CodeGenerationService codeGenerationService;
    
    @PostMapping("/generateCode")
    public ResponseEntity<String> generateCode(@RequestBody CodeGenerationRequest codeGenerationRequest) {
        String generatedCode = codeGenerationService.generateCode(codeGenerationRequest);
        return ResponseEntity.ok(generatedCode);
    }
}

Service:
@Service
public class CodeGenerationService {
    
    @Autowired
    private CodeGenerationRepository codeGenerationRepository;
    
    public String generateCode(CodeGenerationRequest codeGenerationRequest) {
        // Create a request in the database to track the status of the code generation process
        CodeGenerationRequestEntity codeGenerationRequestEntity = new CodeGenerationRequestEntity(codeGenerationRequest);
        codeGenerationRepository.save(codeGenerationRequestEntity);
        
        // Send the request to OpenAI to generate the code
        String generatedCode = sendRequestToOpenAI(codeGenerationRequest);
        
        // Store the generated code in the database along with the GitHub link information
        codeGenerationRequestEntity.setGeneratedCode(generatedCode);
        codeGenerationRepository.save(codeGenerationRequestEntity);
        
        return generatedCode;
    }
    
    private String sendRequestToOpenAI(CodeGenerationRequest codeGenerationRequest) {
        // Send request to OpenAI API and get back generated code
        return generatedCode;
    }
}

Repository:
@Repository
public interface CodeGenerationRepository extends JpaRepository<CodeGenerationRequestEntity, Long> {

}