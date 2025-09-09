package com.exemplo.animais.controller;

import com.exemplo.animais.model.Animal;
import com.exemplo.animais.repository.AnimalRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Animal> listar() {
        List<Animal> animais = repository.findAll();
        System.out.println("Lista de todos os animais:");
        animais.forEach(System.out::println);
        return animais;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        System.out.println("Buscando animal com ID: " + id);
        return repository.findById(id)
                .map(animal -> {
                    System.out.println("Animal encontrado: " + animal);
                    return ResponseEntity.ok(animal);
                })
                .orElseGet(() -> {
                    System.out.println("Animal não encontrado com ID: " + id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Animal> criar(@Valid @RequestBody Animal animal) {
        Animal novo = repository.save(animal);
        System.out.println("Novo animal criado: " + novo);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @Valid @RequestBody Animal dados) {
        System.out.println("Atualizando animal com ID: " + id);
        return repository.findById(id)
                .map(animal -> {
                    animal.setNome(dados.getNome());
                    animal.setEspecie(dados.getEspecie());
                    animal.setIdade(dados.getIdade());
                    repository.save(animal);
                    System.out.println("Animal atualizado: " + animal);
                    return ResponseEntity.ok(animal);
                })
                .orElseGet(() -> {
                    System.out.println("Animal não encontrado para atualização com ID: " + id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        System.out.println("Deletando animal com ID: " + id);
        return repository.findById(id)
                .map(animal -> {
                    repository.delete(animal);
                    System.out.println("Animal deletado: " + animal);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> {
                    System.out.println("Animal não encontrado para deleção com ID: " + id);
                    return ResponseEntity.notFound().build();
                });
    }
}
