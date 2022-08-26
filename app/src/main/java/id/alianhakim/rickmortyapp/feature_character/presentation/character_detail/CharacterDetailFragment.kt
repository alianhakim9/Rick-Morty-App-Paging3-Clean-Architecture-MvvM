package id.alianhakim.rickmortyapp.feature_character.presentation.character_detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.transition.MaterialContainerTransform
import id.alianhakim.rickmortyapp.R
import id.alianhakim.rickmortyapp.databinding.FragmentCharacterDetailBinding
import id.alianhakim.rickmortyapp.feature_character.domain.model.Character

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragmentContainerView
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detail = arguments?.getSerializable("character") as Character
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        binding.avatar.load(detail.image) {
            crossfade(true)
            build()
        }
        binding.name.text = detail.name
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}