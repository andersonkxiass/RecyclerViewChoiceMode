package br.com.anderson.recyclerviewchoicemode;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.anderson.recyclerviewchoicemode.databinding.ActivityMainBinding;
import br.com.anderson.recyclerviewchoicemode.model.Animals;
import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;
import br.com.anderson.recyclerviewchoicemode.viewmodel.AnimalsViewModel;
import br.com.anderson.recyclerviewchoicemode.viewmodel.GroupChoiceActions;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    AnimalsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new AnimalsViewModel(getAnimalList());
        binding.setActions(new GroupChoiceActions());
        binding.setDataModel(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.multi &&
                viewModel.getAdapter().getChoiceMode() != RecyclerChoiceMode.CHOICE_MODE_MULTIPLE) {

            viewModel.actionModeTo(RecyclerChoiceMode.CHOICE_MODE_MULTIPLE);

        } else if (item.getItemId() == R.id.single &&
                viewModel.getAdapter().getChoiceMode() != RecyclerChoiceMode.CHOICE_MODE_SINGLE) {

            viewModel.actionModeTo(RecyclerChoiceMode.CHOICE_MODE_SINGLE);
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Animals> getAnimalList() {
        List<Animals> animalsList = new ArrayList<>();
        animalsList.add(new Animals("Cat"));
        animalsList.add(new Animals("Dog"));
        animalsList.add(new Animals("Shark"));
        animalsList.add(new Animals("Whale"));
        animalsList.add(new Animals("Fish"));
        animalsList.add(new Animals("Monkey"));

        return animalsList;
    }
}
