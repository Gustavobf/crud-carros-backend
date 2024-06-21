package br.com.batista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.dto.ResponseDto;
import br.com.batista.service.DealershipService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/dealership", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DealershipController {

	private DealershipService dealershipService;

	@Autowired
	public DealershipController(DealershipService dealershipService) {
		this.dealershipService = dealershipService;
	}

	@ApiOperation("Returns a list with all dealerships")
	@GetMapping("/getall")
	public ResponseEntity<Page<DealershipDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(dealershipService.getAll(pageable));
	}

	@ApiOperation("Returns a dealership based on its id")
	@GetMapping("/getbyid")
	public ResponseEntity<DealershipDTO> getById(@RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(dealershipService.getById(id));
	}

	@ApiOperation("Saves a dealership")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final DealershipDTO dealershipDTO) {
		dealershipService.create(dealershipDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
	}

	@ApiOperation("Deletes a dealership based on its id")
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@RequestParam final Long id) {
		dealershipService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}

	@ApiOperation("Updates a dealership")
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final DealershipDTO dealershipDTO) {
		dealershipService.update(dealershipDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}

}