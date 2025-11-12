package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthorsPage(@RequestParam(required = false) String error,
                               Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Author> authors;

        authors = authorService.listAll();

        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/author-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author-form";
    }

    @GetMapping("/author-form/{id}")
    public String getEditAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id).orElse(null);
        if (author == null) {
            return "redirect:/authors?error=AuthorNotFound";
        }
        model.addAttribute("author", author);
        return "author-form";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        authorService.save(null, name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/edit/{authorId}")
    public String editAuthor(@PathVariable Long authorId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        authorService.update(authorId, name, surname, country, biography);
        return "redirect:/authors";
    }


    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}

